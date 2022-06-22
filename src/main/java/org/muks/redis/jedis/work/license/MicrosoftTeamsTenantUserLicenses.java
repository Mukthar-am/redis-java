package org.muks.redis.jedis.work.license;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.muks.redis.jedis.work.license.license.TenantCspUserLicenseIngestion;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MicrosoftTeamsTenantUserLicenses {

  private int totalTeamsSkuUserLicenses = 0;
  private JSONObject subscribedSkuJsonString = null;


  public MicrosoftTeamsTenantUserLicenses(JSONObject subscribedSkuJsonString) {
    this.subscribedSkuJsonString = subscribedSkuJsonString;
  }


  public static void main(String[] args) throws IOException {

    String skuFile = "/Users/mmaniyar/Downloads/subscribedSkus.txt";

    // get skuJson
    JSONObject skuJson = parseJSONFile(skuFile);

    MicrosoftTeamsTenantUserLicenses microsoftTeamsTenantUserLicenses = new MicrosoftTeamsTenantUserLicenses(skuJson);
    int userLicenses = microsoftTeamsTenantUserLicenses.getUserLicenseCount();
    System.out.println("Microsoft Teams, Tenant User Licenses: " + userLicenses);



    TenantCspUserLicenseIngestion tenantCspUserLicenseIngestion = new TenantCspUserLicenseIngestion();
    String key = tenantCspUserLicenseIngestion.makeTenantCspUserLicenseKey();
    System.out.println("key: " + key);

    tenantCspUserLicenseIngestion.persistUserLicensesData(key, userLicenses);

  }


  private int getUserLicenseCount() {
    // SKUs reside into value array
    String skuValueJsonKey = "value";

    if (this.subscribedSkuJsonString.has(skuValueJsonKey)) {
      JSONArray tenantSkus = this.subscribedSkuJsonString.getJSONArray(skuValueJsonKey);
      System.out.println("\nTotal tenant SKUs: " + tenantSkus.length());

      tenantSkus.iterator().forEachRemaining(element -> {
        System.out.println("\n");
        int teamsSkuUserLicenses = getTeamsSkuUserLicenses((JSONObject) element);
        System.out.println("teamsSkuUserLicenses: " + teamsSkuUserLicenses);
        totalTeamsSkuUserLicenses += teamsSkuUserLicenses;
      });

    } else {
      System.out.println("Empty Subscribed Sku response.");
    }

    return this.totalTeamsSkuUserLicenses;
  }

  private int getTeamsSkuUserLicenses(JSONObject subscribedSku) {
    System.out.println("\nsubscribedSkus: " + subscribedSku);

    if (isOfTeamsCapabilityStatusSku(subscribedSku) && isOfTeamsServicePlanSku(subscribedSku)) {
      return getSkuPrepaidUnits(subscribedSku);
    }

    return 0;
  }


  private boolean isOfTeamsServicePlanSku(JSONObject subscribedSkus) {
    List<String> servicePlanIds = getServicePlanIds(subscribedSkus);
    System.out.println("servicePlanIds : " + servicePlanIds);

    // ToDo configurable
    List<String> validTeamsServicePlans =
        Arrays.asList("6dc145d6-95dd-4191-b9c3-185575ee6f6b", "57ff2da0-773e-42df-b2af-ffb7a2317929");

    List<String> validServicePlan = servicePlanIds.stream()
        .filter(planId -> validTeamsServicePlans.contains(planId))
        .collect(Collectors.toList());

    return (validServicePlan.size() > 0);
  }

  private List<String> getServicePlanIds(JSONObject subscribedSkus) {
    List<String> servicePlanIds = new ArrayList<>();

    JSONArray skuServicePlans = getSkuServicePlans(subscribedSkus);

    skuServicePlans.iterator().forEachRemaining(servicePlan -> {
      servicePlanIds.add(getServicePlanId((JSONObject) servicePlan));
    });

    return servicePlanIds;
  }

  private JSONArray getSkuServicePlans(JSONObject subscribedSkus) {
    String servicePlansJsonKey = "servicePlans";
    JSONArray servicePlans = null;

    if (subscribedSkus.has(servicePlansJsonKey)) {
      servicePlans = subscribedSkus.getJSONArray(servicePlansJsonKey);
      System.out.println("servicePlans: " + servicePlans);
    }

    return servicePlans;
  }

  private String getServicePlanId(JSONObject servicePlan) {
    return servicePlan.get("servicePlanId").toString();
  }

  private boolean isOfTeamsCapabilityStatusSku(JSONObject subscribedSkus) {
    String capabilityStatusJsonKey = "capabilityStatus";
    boolean isTeamsEnabledCapabilityLicense = false;

    if (subscribedSkus.has(capabilityStatusJsonKey)) {
      String capabilityStatus = subscribedSkus.getString(capabilityStatusJsonKey);

      if (capabilityStatus.equalsIgnoreCase("enabled") ||
          capabilityStatus.equalsIgnoreCase("warning") ||
          capabilityStatus.equalsIgnoreCase("warn")) {

        isTeamsEnabledCapabilityLicense = true;
      }
    }
    return isTeamsEnabledCapabilityLicense;
  }

  private int getSkuPrepaidUnits(JSONObject subscribedSku) {
    String prepaidUnitsJsonKey = "prepaidUnits";
    return subscribedSku.getJSONObject(prepaidUnitsJsonKey).getInt("enabled");

  }

  public static JSONObject parseJSONFile(String filename) throws JSONException, IOException, IOException {
    String content = new String(Files.readAllBytes(Paths.get(filename)));
    return new JSONObject(content);
  }

}

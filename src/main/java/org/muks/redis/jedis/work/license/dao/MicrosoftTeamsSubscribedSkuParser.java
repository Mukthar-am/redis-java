package org.muks.redis.jedis.work.license.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: @mukthar.maniyar
 * Dt: 23-June'22
 * Purpose:
 *  - fetch total user licenses by parsing SKUs
 *  - SKU parsing logic:
 *      - filter SKUs which have capabilityStatus as Enabled or Warning
 *      - plus, the SKU are eitehr of service-plans "validTeamsServicePlans"
 *      - fetch, the value of "prepaidUnits:Enabled" from all those above matched SKUs
 */


public class MicrosoftTeamsSubscribedSkuParser {
  private MicrosoftTeamsSubscribedSkus microsoftTeamsSubscribedSkus;
  private int totalTeamsUserLicenses = 0;
  private final List<String> validTeamsServicePlans =
      Arrays.asList("6dc145d6-95dd-4191-b9c3-185575ee6f6b", "57ff2da0-773e-42df-b2af-ffb7a2317929");

  public MicrosoftTeamsSubscribedSkuParser(MicrosoftTeamsSubscribedSkus microsoftTeamsSubscribedSkus) {
    this.microsoftTeamsSubscribedSkus = microsoftTeamsSubscribedSkus;
  }

  public int getUserLicenseCount() {
    List<Sku> subscribedSkus = this.microsoftTeamsSubscribedSkus.getValue();
    int totalSkus = subscribedSkus.size();
    if (totalSkus > 0) {
      System.out.println("MicrosoftTeamsSubscribedSkuParser has " + totalSkus + " subscribed SKUs to parse.");

      List<Sku> validTeamsSku = subscribedSkus.stream()
          .filter(this::isSkuOfTeamsCapabilityStatus)
          .filter(this::isSkuOfTeamsServicePlans)
          .collect(Collectors.toList());

      System.out.println("validTeamsSku: " + validTeamsSku.size() + "\nvalidTeamsSku: " + toPrettyJson(validTeamsSku));

      validTeamsSku.stream().forEach(sku -> {
        int enabledPrepaidUnits = sku.getPrepaidUnits().getEnabled();
        this.totalTeamsUserLicenses += enabledPrepaidUnits;
        System.out.println("SkuId: " + sku.getSkuId() + " has Enabled-PrepaidUnits: " + enabledPrepaidUnits);
      });
    }
    return this.totalTeamsUserLicenses;
  }


  private boolean isSkuOfTeamsCapabilityStatus(Sku subscribedSku) {
    boolean hasTeamsCapabilityStatus = false;
    String skuCapabilityStatus = subscribedSku.getCapabilityStatus();

    if (skuCapabilityStatus != null &&
        (skuCapabilityStatus.equalsIgnoreCase("enabled")
            || skuCapabilityStatus.equalsIgnoreCase("warning"))) {
      hasTeamsCapabilityStatus = true;
    }

    return hasTeamsCapabilityStatus;
  }

  private boolean isSkuOfTeamsServicePlans(Sku subscribedSku) {
    boolean isSkuWithValidTeamsServicePlan = false;

    List<ServicePlan> servicePlans = subscribedSku.getServicePlans();
    List<ServicePlan> validServicePlans =
        servicePlans
            .stream()
            .filter(this::isValidTeamsServicePlan)
            .collect(Collectors.toList());

    if (validServicePlans.size() > 0) {
      isSkuWithValidTeamsServicePlan = true;
    }

    return isSkuWithValidTeamsServicePlan;
  }

  private boolean isValidTeamsServicePlan(ServicePlan servicePlan) {
    return this.validTeamsServicePlans.contains(servicePlan.getServicePlanId());
  }

  public String toString() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  // UnrecognizedPropertyException

    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this.microsoftTeamsSubscribedSkus);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }

  private String toPrettyJson(Object obj) {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  // UnrecognizedPropertyException

    try {
      return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return null;
    }
  }
}

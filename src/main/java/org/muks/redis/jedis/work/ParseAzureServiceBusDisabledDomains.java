package org.muks.redis.jedis.work;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class ParseAzureServiceBusDisabledDomains {

  private String azureManagementUrl = "https://management.azure.com/subscriptions/efa883c5-adb1-45bd-bb33-6c3b2ae8435c/resourceGroups/prod-northcentral/providers/Microsoft.ServiceBus/namespaces/shn-sharepoint-dlp-events-prod-new/queues?api-version=2017-04-01&$skip=0&$top=100&_=1655557124700";
  private String authToken = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsIng1dCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyIsImtpZCI6ImpTMVhvMU9XRGpfNTJ2YndHTmd2UU8yVnpNYyJ9.eyJhdWQiOiJodHRwczovL21hbmFnZW1lbnQuY29yZS53aW5kb3dzLm5ldC8iLCJpc3MiOiJodHRwczovL3N0cy53aW5kb3dzLm5ldC81MTZlYmNiNS1mNGM0LTQzZTktYjJkZi0zNzNiMGMxM2EwZjMvIiwiaWF0IjoxNjU1NzIzOTkzLCJuYmYiOjE2NTU3MjM5OTMsImV4cCI6MTY1NTcyNzkyMiwiYWNyIjoiMSIsImFpbyI6IkFVUUF1LzhUQUFBQXJTQ3FlMFZHUFRoS08zV25tNnlvdkNqSUkyV285emxuTndqaU91WjNSRHE2SEFFd0VRWjJ4Q3NaSDRPenF6STFRRDlKSlhVZk8vZkcrbHU5ZkFjdnRBPT0iLCJhbHRzZWNpZCI6IjE6bGl2ZS5jb206MDAwM0JGRkRCQzBBNUIwMSIsImFtciI6WyJwd2QiXSwiYXBwaWQiOiJjNDRiNDA4My0zYmIwLTQ5YzEtYjQ3ZC05NzRlNTNjYmRmM2MiLCJhcHBpZGFjciI6IjIiLCJlbWFpbCI6InNobnN3QHNreWhpZ2huZXR3b3Jrcy5jb20iLCJmYW1pbHlfbmFtZSI6IkhpZ2giLCJnaXZlbl9uYW1lIjoiU2t5IiwiZ3JvdXBzIjpbImVmNjIzZDBjLTM4M2EtNGU5NS1iMzYzLThjMDgxYTJmNzBkOCJdLCJpZHAiOiJsaXZlLmNvbSIsImlwYWRkciI6IjEwMy4xNi42OS43NiIsIm5hbWUiOiJTa3kgSGlnaCIsIm9pZCI6IjE3NTBkZGUyLTUwNjMtNGE5OC1iZTAyLWZlZGI0Y2YyZTJhZCIsInB1aWQiOiIxMDAzN0ZGRUEyNEVGRjA4IiwicmgiOiIwLkFYQUF0Ynh1VWNUMDZVT3kzemM3REJPZzgwWklmM2tBdXRkUHVrUGF3ZmoyTUJOd0FOby4iLCJzY3AiOiJ1c2VyX2ltcGVyc29uYXRpb24iLCJzdWIiOiIyTk9SMVZfaEhjNGlwdXJYeDlxMGRxb0h1VmVsMzQxVmtwcWo3bEw4aEFZIiwidGlkIjoiNTE2ZWJjYjUtZjRjNC00M2U5LWIyZGYtMzczYjBjMTNhMGYzIiwidW5pcXVlX25hbWUiOiJsaXZlLmNvbSNzaG5zd0Bza3loaWdobmV0d29ya3MuY29tIiwidXRpIjoieHpzMFo3cnBjVTJwVm00Wm5NVTNBUSIsInZlciI6IjEuMCIsIndpZHMiOlsiNjJlOTAzOTQtNjlmNS00MjM3LTkxOTAtMDEyMTc3MTQ1ZTEwIl0sInhtc190Y2R0IjoxNDk3NDQwODgwfQ.LJrUeohtAW7OXmBXqS5UYK06rMcfwcxL-aHv0uzmi0gRoM0r22v6U51KZbP-OLgrnx24w0quEuEqmKYfwoqDwKZ3WZQa40zgGTbmBsUtRtj-p4r9FF4Q1a_zVaexMlweom7SyH1z6x_yheVhx-4ixCVgniJb7Z20nW1AvmuDW6TJXxYXhBX67clIKubEQbb8MkwarI2Ri_JqtEgIsv840OSaePVzuwpZPS0mT6u2dzjiskUq0uR6s4dMIFAnBiL6B0dSMCsckYcAfTH8H63b_mqT2GLJKaICaajRQeVfJiYEmaowHVDf-aNxKROhcqBKoK9RtNfuCX0hmUdt8dKUbA";

  private String nextLinkKey = "nextLink";

  public static JSONObject parseJSONFile(String filename) throws JSONException, IOException, IOException {
    String content = new String(Files.readAllBytes(Paths.get(filename)));
    return new JSONObject(content);
  }

  public static void main(String[] args) throws IOException {
    ParseAzureServiceBusDisabledDomains parseAzureServiceBusDisabledDomains = new ParseAzureServiceBusDisabledDomains();
    //parseDisabledDomains.sendGet(parseDisabledDomains.azureManagementUrl);

    Map<String, List<String>> domainsMap = new HashMap<>();
    parseAzureServiceBusDisabledDomains.getAzureServiceBusQueues(parseAzureServiceBusDisabledDomains.azureManagementUrl, domainsMap);

    System.out.println("total queues from Azure ServiceBus: " + domainsMap.get("domains").size() + ", \nQueues: " + domainsMap.get("domains"));
    Path output = Paths.get("/Users/mmaniyar/Downloads/azureServiceBusQueues.txt");
    List<String> azureServiceBusQueues = domainsMap.get("domains");
    try {
      Files.write(output, domainsMap.get("domains"));
    } catch (Exception e) {
      e.printStackTrace();
    }


    AzureRedisTests azureRedisTests = new AzureRedisTests();
    List<String> azureRedisDomains = azureRedisTests.azureRedisCacheConnect();
    azureRedisTests.azureRedisCacheConnClose();

    Path azureRedisQueuesFile = Paths.get("/Users/mmaniyar/Downloads/azureRedisQueues.txt");

    try {
      Files.write(azureRedisQueuesFile, azureRedisDomains);
    } catch (Exception e) {
      e.printStackTrace();
    }


    parseAzureServiceBusDisabledDomains.findList(azureRedisDomains, azureServiceBusQueues);
  }


  private void findList(List<String> list1, List<String> list2) {
//    List<String> diff = new ArrayList<>();
//    for (String queue : list2) {
//      if (!list1.contains(queue)) {
//        diff.add(queue);
//      }
//    }

    list2.removeAll(list1);

    System.out.println("Difference: " + list2.size() + ", = " + list2);
  }


  private void getAzureServiceBusQueues(String azureManagementUrl, Map<String, List<String>> domainsMap) {
    URL url = null;
    HttpURLConnection con = null;
    StringBuffer response = new StringBuffer();

    try {
      url = new URL(azureManagementUrl);
      con = (HttpURLConnection) url.openConnection();

      con.setRequestMethod("GET");

      con.setRequestProperty("Content-Type", "application/json");
      con.setRequestProperty("Authorization", authToken);

      int responseCode = con.getResponseCode();
      System.out.println("GET Response Code :: " + responseCode);

      if (responseCode == HttpURLConnection.HTTP_OK) { // success
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null) {
          response.append(inputLine);
        }
        in.close();
      } else {
        System.out.println("GET request not worked");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    Map<String, List<String>> domainsandNextLink = getDomainsAndNextLink(response);
    List<String> pagedDomains = domainsandNextLink.get("domains");
    if (domainsMap.containsKey("domains")) {
      domainsMap.get("domains").addAll(pagedDomains);
    } else {
      domainsMap.put("domains", pagedDomains);
    }

    if (domainsandNextLink.containsKey(nextLinkKey)) {
      String nextLink = domainsandNextLink.get(nextLinkKey).get(0);
      getAzureServiceBusQueues(nextLink, domainsMap);
    }

    //return domainsMap;
  }

  private Map<String, List<String>> getDomainsAndNextLink(StringBuffer httpResponse) {
    Map<String, List<String>> domainsMap = new HashMap<>();
    List<String> domains = new ArrayList<>();
    JSONObject jsonObject = new JSONObject(httpResponse.toString());
    Iterator<String> keys = jsonObject.keys();

    while (keys.hasNext()) {
      String key = keys.next();

      if (key.equals("value")) {
        JSONArray jsonarray = (JSONArray) jsonObject.get(key);
        for (int i = 0; i < jsonarray.length(); i++) {
          JSONObject jsonobject = jsonarray.getJSONObject(i);
          String name = jsonobject.getString("name");
          domains.add(name);
        }
      }

      if (jsonObject.has(nextLinkKey)) {
        String nextLink = jsonObject.get(key).toString();
        //System.out.println("nextLink: " + nextLink);
        domainsMap.put(nextLinkKey, Arrays.asList(nextLink));
      }
    }

    domainsMap.put("domains", domains);

    return domainsMap;
  }


}

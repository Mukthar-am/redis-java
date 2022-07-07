package org.muks.redis.jedis.work.license.license;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.muks.redis.jedis.work.license.dao.MicrosoftTeamsSubscribedSkuParser;
import org.muks.redis.jedis.work.license.dao.MicrosoftTeamsSubscribedSkus;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SkuSerializationTests {

  public static void main(String[] args) throws IOException {
    String input = "/Users/mmaniyar/Downloads/sku.json";
    String subscribedSkuValueFile = "/Users/mmaniyar/Downloads/subscribedSkuValueFile.json";

    String jsonString = parseJSONFile(input);
    String skusFile = parseJSONFile(subscribedSkuValueFile);


    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  // UnrecognizedPropertyException


    MicrosoftTeamsSubscribedSkus subscribedSkus = objectMapper.readValue(skusFile, MicrosoftTeamsSubscribedSkus.class);

    MicrosoftTeamsSubscribedSkuParser microsoftTeamsSubscribedSkuParser = new MicrosoftTeamsSubscribedSkuParser(subscribedSkus);
    int totalTeamsUserLicenses = microsoftTeamsSubscribedSkuParser.getUserLicenseCount();
    System.out.println("totalTeamsUserLicenses: " + totalTeamsUserLicenses);
  }


  public static String parseJSONFile(String filename) throws JSONException, IOException, IOException {
    return new String(Files.readAllBytes(Paths.get(filename)));
  }
}

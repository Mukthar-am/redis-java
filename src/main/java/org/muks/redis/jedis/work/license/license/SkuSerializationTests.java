package org.muks.redis.jedis.work.license.license;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONException;
import org.json.JSONObject;
import org.muks.redis.jedis.work.license.dao.Sku;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SkuSerializationTests {

  public static void main(String[] args) throws IOException {
    String input = "/Users/mmaniyar/Downloads/sku.json";

    String jsonString = parseJSONFile(input);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  // UnrecognizedPropertyException

    Sku readValue = objectMapper.readValue(jsonString, Sku.class);
    System.out.println("readValue = " + readValue);


  }


  public static String parseJSONFile(String filename) throws JSONException, IOException, IOException {
    return new String(Files.readAllBytes(Paths.get(filename)));
  }
}

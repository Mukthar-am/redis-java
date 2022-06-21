//package org.muks.redis.jedis.work;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import org.json.JSONObject;
//
//import java.io.DataInput;
//import java.io.IOException;
//import java.util.Map;
//
//public class SubSkuTesting {
//
//  public static void main(String[] args) throws IOException {
//
//    String skuFile = "/Users/mmaniyar/Downloads/subscribedSkus.txt";
//
//    // get skuJson
//    JSONObject skuJson = subscribedSkus.parseJSONFile(skuFile);
//    SubscribedSkus subscribedSkus = new SubscribedSkus();
//
//    ObjectMapper mapper = new ObjectMapper();
//    mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
//    MicrosoftSubscribedSku msftSubscribedSku = mapper.readValue(skuJson.get("value").toString(), MicrosoftSubscribedSku.class);
//    System.out.println(msftSubscribedSku);
//
//
//  }
//}

package org.muks.redis.jedis.work;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RedisExtractTests {

  protected static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static void main(String[] args) {
//    objectMapper.writeValueAsString(channel)
  }

}

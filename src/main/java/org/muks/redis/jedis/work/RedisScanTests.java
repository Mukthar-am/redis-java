package org.muks.redis.jedis.work;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ScanParams;
import redis.clients.jedis.resps.ScanResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static redis.clients.jedis.params.ScanParams.SCAN_POINTER_START;

public class RedisScanTests {
  private static String host = "127.0.0.1";
  private static int port = 6379;

  static String offlineDlpPrefix = "offlinedlp";
  static String dlpEventStats = "dlp";
  static int tenantId = 2229;
  static int tenantId2 = 4300;
  static int cspId = 25680;
  static int instanceId = 1212;
  static int instanceId2 = 7210;
  static int instanceId3 = 2212;
  static String cloudServiceStatsCacheKey = "cloud_service_stats";

  private static Jedis JedisClient = null;
  protected static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static void main(String[] args) {
    redisLocalCacheConnect();

    String cloudStatsKey1 = Joiner.on(":").join(offlineDlpPrefix, dlpEventStats, cloudServiceStatsCacheKey);
    String cloudStatsKeyPattern = Joiner.on("").join(cloudStatsKey1, "*");

    List<String> statsKeysToRead = null;
    try {
      statsKeysToRead = getUsageStatsCacheKeys(cloudStatsKeyPattern);
      System.out.println("Reading total of " + statsKeysToRead.size() + " Redis keys - " + statsKeysToRead);

      scanUsageStatsCacheKeys();

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

  }

  /**
   * ============== logically methods ==============
   */

  private static void scanUsageStatsCacheKeys() {
    ScanParams scanParams = new ScanParams().count(1).match("offlinedlp:dlp:cloud_service_stats:*");
    String cur = SCAN_POINTER_START;
    do {
      ScanResult<String> scanResult = JedisClient.scan(cur, scanParams);

      // work with result
      scanResult.getResult().stream().forEach(
          System.out::println
      );
      cur = scanResult.getCursor();
    } while (!cur.equals(SCAN_POINTER_START));
  }


  private static List<String> getUsageStatsCacheKeys(String redisKeyPattern) throws JsonProcessingException {
    List<String> availableKeys = new ArrayList<>();
    Set<String> statsCacheKeys = JedisClient.keys(redisKeyPattern);

    statsCacheKeys.forEach(statsKey -> {
      String[] redisStatsKeySplits = statsKey.split(":");
      int statsKeySplitLength = redisStatsKeySplits.length - 1;

      System.out.println("len: " + statsKeySplitLength);
      String instanceId = redisStatsKeySplits[statsKeySplitLength];
      String cspId = redisStatsKeySplits[statsKeySplitLength-1];
      String tenantId = redisStatsKeySplits[statsKeySplitLength-2];
      System.out.println("Instance: " + redisStatsKeySplits[statsKeySplitLength]);

      availableKeys.add(statsKey);
    });
    return availableKeys;
  }

  private static void redisLocalCacheConnect() {
    boolean useSsl = false;
    // Connect to the Azure Cache for Redis over the TLS/SSL port using the key.
    JedisClient = new Jedis(host, port, DefaultJedisClientConfig.builder()
        .ssl(useSsl)
        .build());

    // Simple PING command
    System.out.println( "\nCache Command  : Ping" );
    System.out.println( "Cache Response : " + JedisClient.ping());
  }
}

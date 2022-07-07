package org.muks.redis.jedis.work;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.muks.commons.dao.UsageMetrices;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Ref: https://www.baeldung.com/jedis-java-redis-client-library
 */


public class LocalRedisAggregationTests {

  private static String host = "127.0.0.1";
  private static int port = 6379;

  static String offlineDlpPrefix = "offlinedlp";
  static String dlpEventStats = "dlp";
  static String cloudServiceStatsCacheKey = "cloud_service_stats";
  static int cspId = 25680;
  static int tenantId = 10402;
  static int instanceId = 28119;
  static int api = 1;

  static int tenantId2 = 20402;
  static int instanceId2 = 38119;

  private static Jedis JedisClient = null;
  protected static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }


  private static String getTimeNow() {
    return Instant.now().toString();
  }

  public static void main(String[] args) {
    try {
      UsageMetrices stats1 = UsageMetrices.builder()
          .setTimestamp("2022-05-01T02:00:00.000Z")
          .setTenantId(tenantId)
          .setInstanceId(instanceId)
          .setCspId(cspId)
          .setApi(2)
          .setCount(5)
          .build();

      UsageMetrices stats2 = UsageMetrices.builder()
          .setTimestamp("2022-05-01T03:00:00.000Z")
//          .setTimestamp(getTimeNow())
          .setTenantId(tenantId)
          .setInstanceId(instanceId)
          .setCspId(cspId)
          .setApi(2)
          .setCount(3)
          .build();

      UsageMetrices stats3 = UsageMetrices.builder()
          .setTimestamp(getTimeNow())
          .setTenantId(tenantId)
          .setInstanceId(instanceId)
          .setCspId(cspId)
          .setApi(3)
          .setCount(51)
          .build();

      System.out.println("stats1: " + objectMapper.writeValueAsString(stats1));
      System.out.println("\n");

      redisLocalCacheConnect();

//      ingestStatsToRedis(stats1);
//      ingestStatsToRedis(stats2);
//      ingestStatsToRedis(stats2);

      ingestStatsToRedisHset(stats1);
      ingestStatsToRedisHset(stats2);

      System.exit(0);

//      increamentBy(cloudStatsKey2, stats2);
//      increamentBy(keyType21, stats3);

//      List<String> statsKeysToRead = getUsageStatsCacheKeys(cloudStatsKeyPattern);
//      System.out.println("Reading total of " + statsKeysToRead.size() + " Redis keys - " + statsKeysToRead);
//
//
//      statsKeysToRead.forEach(key -> {
//        System.out.println("key: " + key);
//        String[] splits = key.split(":");
//
//        int length = splits.length;
//        System.out.printf("Len: " + length);
//        String tenantInstanceKey = Joiner.on(":").join(splits[length-3], splits[length-2], splits[length-1]);
//        System.out.println(tenantInstanceKey);
//
//        //TenantInstanceKey tenantInstanceKey = TenantInstanceKey.create(tenantId, cspId, instanceId);
//      });

      System.exit(0);

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  private static void readHset(String key) {
    Map<String, String> hsetEntries = JedisClient.hgetAll(key);
    System.out.println("hsetEntries: " + hsetEntries);
  }

  /**
   * ============== logically methods ==============
   */
  private static void ingestStatsToRedisHset(UsageMetrices usageMetrices) {
    String redisKeyHset = KeyMaker.makeUsageStatsCacheKeyHset(usageMetrices);
    System.out.println("redisKey: " + redisKeyHset);

    String timeNow = usageMetrices.getTimestamp();
    String[] dateTimeSplits = timeNow.split("T");
    String[] timeFieldSplits = dateTimeSplits[1].split(":");

    JedisClient.hincrBy(redisKeyHset, timeFieldSplits[0], usageMetrices.getCount());

    readHset(redisKeyHset);
  }

  private static void ingestStatsToRedis(UsageMetrices usageMetrices) {
    String redisKey = KeyMaker.makeUsageStatsCacheKey(usageMetrices);
    System.out.println("redisKey: " + redisKey);

    JedisClient.incrBy(redisKey, usageMetrices.getCount());
  }

  static class KeyMaker {

    static String CLOUD_SERVICE_STATS_KEY = "offlinedlp:dlp:cloud_service_stats";

    static String makeUsageStatsCacheKey(UsageMetrices usageMetrices) {
      String timeNow = usageMetrices.getTimestamp();
      String[] dateTimeSplits = timeNow.split("T");
      String[] dateFieldSplits = dateTimeSplits[0].split("-");
      String[] timeFieldSplits = dateTimeSplits[1].split(":");

      return Joiner.on(":").join(CLOUD_SERVICE_STATS_KEY,
          usageMetrices.getTenantId(),
          usageMetrices.getCspId(),
          usageMetrices.getInstanceId(),
          usageMetrices.getApi(),
          dateFieldSplits[0],
          dateFieldSplits[1],
          dateFieldSplits[2],
          timeFieldSplits[0]
      );
    }

    static String makeUsageStatsCacheKeyHset(UsageMetrices usageMetrices) {
      String timeNow = usageMetrices.getTimestamp();
      String[] dateTimeSplits = timeNow.split("T");
      String[] dateFieldSplits = dateTimeSplits[0].split("-");
      String[] timeFieldSplits = dateTimeSplits[1].split(":");

      return Joiner.on(":").join(CLOUD_SERVICE_STATS_KEY,
          usageMetrices.getTenantId(),
          usageMetrices.getCspId(),
          usageMetrices.getInstanceId(),
          usageMetrices.getApi(),
          dateTimeSplits[0]
      );
    }
  }


  private static String makeRedisKey(UsageMetrices usageMetrices) {
    String timeNow = usageMetrices.getTimestamp();
    String[] dateTimeSplits = timeNow.split("T");
    String[] dateFieldSplits = dateTimeSplits[0].split("-");
    String[] timeFieldSplits = dateTimeSplits[1].split(":");

    return Joiner.on(":").join(offlineDlpPrefix, dlpEventStats, cloudServiceStatsCacheKey,
        usageMetrices.getTenantId(),
        usageMetrices.getCspId(),
        usageMetrices.getInstanceId(),
        usageMetrices.getApi(),
        dateFieldSplits[0],
        dateFieldSplits[1],
        dateFieldSplits[2],
        timeFieldSplits[0]);
  }

  private static String makeRedisKeyType2(UsageMetrices usageMetrices) {
    String timeNow = usageMetrices.getTimestamp();
    String[] dateTimeSplits = timeNow.split("T");
    String[] dateFieldSplits = dateTimeSplits[0].split("-");
    String[] timeFieldSplits = dateTimeSplits[1].split(":");

    return Joiner.on(":").join(offlineDlpPrefix, dlpEventStats, cloudServiceStatsCacheKey,
        usageMetrices.getTenantId(),
        usageMetrices.getCspId(),
        usageMetrices.getInstanceId(),
        usageMetrices.getApi(),
        dateTimeSplits[0],
        timeFieldSplits[0],
        timeFieldSplits[1]);
  }


  private static List<String> getUsageStatsCacheKeys(String redisKeyPattern) throws JsonProcessingException {
    List<String> availableKeys = new ArrayList<>();
    Set<String> statsCacheKeys = JedisClient.keys(redisKeyPattern);

    statsCacheKeys.forEach(statsKey -> {
      String[] redisStatsKeySplits = statsKey.split(":");
      int statsKeySplitLength = redisStatsKeySplits.length - 1;

      System.out.println("len: " + statsKeySplitLength);
      String instanceId = redisStatsKeySplits[statsKeySplitLength];
      String cspId = redisStatsKeySplits[statsKeySplitLength - 1];
      String tenantId = redisStatsKeySplits[statsKeySplitLength - 2];
      System.out.println("Instance: " + redisStatsKeySplits[statsKeySplitLength]);

      availableKeys.add(statsKey);
    });
    return availableKeys;
  }

  private static void fetchStats(List<String> keys, List<UsageMetrices> stats)
      throws JsonProcessingException {

    keys.forEach(k -> {
      try {
        stats.addAll(fetchStats(k));

      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    System.out.println("returning total of " + stats.size() + " stats - " + stats);
  }

  private static List<UsageMetrices> fetchStats(String redisKey) throws JsonProcessingException {
    System.out.println("Reading key - " + redisKey);
    List<UsageMetrices> stats = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    Set<String> statsRecords = JedisClient.spop(redisKey, JedisClient.scard(redisKey));
    statsRecords.forEach(name -> {
      try {
        stats.add(mapper.readValue(name, UsageMetrices.class));

      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    return stats;
  }


  private static List<UsageMetrices> fetch(String key) throws JsonProcessingException {
    List<String> keys = getUsageStatsCacheKeys(key);
    return fetchStatsFromSet(keys);
  }

  private static List<UsageMetrices> fetchStatsFromSet(List<String> keys) throws JsonProcessingException {
    List<UsageMetrices> stats = new ArrayList<>();

    keys.forEach(k -> {
      try {
        stats.addAll(fetchStatsByKeyFromSet(k));

      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    return stats;
  }

  private static List<UsageMetrices> fetchStatsByKeyFromSet(String redisKey) throws JsonProcessingException {
    List<UsageMetrices> stats = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    Set<String> statsRecords = JedisClient.spop(redisKey, JedisClient.scard(redisKey));
    statsRecords.forEach(name -> {
      try {
        stats.add(mapper.readValue(name, UsageMetrices.class));

      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
    });

    return stats;
  }

//  private static List<UsageMetrices> fetchStats(String redisKey) throws JsonProcessingException {
//    List<UsageMetrices> stats = new ArrayList<>();
//    ObjectMapper mapper = new ObjectMapper();
//
//    Map<String, String> statsRecords = JedisClient.hgetAll(redisKey);
//    statsRecords.forEach((key, value) -> {
//      try {
//        stats.add(mapper.readValue(value, UsageMetrices.class));
//
//      } catch (JsonProcessingException e) {
//        e.printStackTrace();
//      }
//
//    });
//
//    return stats;
//  }


  private static void hGet(String redisKey, String hKey) {
    System.out.println();
    String value = JedisClient.hget(redisKey, hKey);
    System.out.println("Running hGet: " + value);
  }

  private static void deleteKey(String key) {
    JedisClient.del(key);
  }

  private static void writeToHSet(String key, UsageMetrices data) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    String statsAsString = objectMapper.writeValueAsString(data);
    JedisClient.hset(key, String.valueOf(data.getTimestamp()),
        statsAsString);  // ingest into redis in json string format

    // fetch values and typecast back to the ojbect
    Map<String, String> allItemsFromKey = JedisClient.hgetAll(key);
    Set<String> keys = allItemsFromKey.keySet();
    for (String k : keys) {
      UsageMetrices castedBackObj = objectMapper.readValue(allItemsFromKey.get(k), UsageMetrices.class);
    }
  }


  private static void writeToSet(String key, UsageMetrices data) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    String statsAsString = objectMapper.writeValueAsString(data);
//    JedisClient.set(key, String.valueOf(data.getCount()));
    JedisClient.sadd(key, String.valueOf(data.getCount()));
  }

  private static void getSet(String key) throws JsonProcessingException {

    Set<String> stats = JedisClient.smembers(key);
    Iterator<String> setItr = stats.iterator();
    while (setItr.hasNext()) {
      UsageMetrices usageMetrices = objectMapper.readValue(setItr.next(), UsageMetrices.class);
      System.out.println(usageMetrices);
    }
  }

  private static void writeString(String key, String data) {
    JedisClient.set(key, data);
    String cachedResponse = JedisClient.get(key);
    System.out.println("data: " + cachedResponse);
  }

  private static void writeString(String key) {
    JedisClient.set(key, "32,15,223,828");
    String cachedResponse = JedisClient.get(key);

    System.out.println("data: " + cachedResponse);

    JedisClient.set(key, "mukthar");
    String cachedResponse2 = JedisClient.get(key);
    System.out.println("data: " + cachedResponse2);
  }

  public static boolean isBlankCheck(String str) {
    return Strings.nullToEmpty(str).trim().isEmpty();
  }

  private static void redisConnect() {
    JedisClient = new Jedis(Protocol.DEFAULT_HOST);
    JedisClient.connect();

    if (JedisClient.ping().toLowerCase().equals("pong")) {
      System.out.println("connected...");

      //check whether server is running or not
      System.out.println("Server is running: " + JedisClient.ping());
    } else {
      System.out.println("problem in connection to redis");
    }
  }

  private static void redisLocalCacheConnect() {
    boolean useSsl = false;
    // Connect to the Azure Cache for Redis over the TLS/SSL port using the key.
    JedisClient = new Jedis(host, port, DefaultJedisClientConfig.builder()
        .ssl(useSsl)
        .build());

    // Simple PING command
    System.out.println("\nCache Command  : Ping");
    System.out.println("Cache Response : " + JedisClient.ping());
  }
}

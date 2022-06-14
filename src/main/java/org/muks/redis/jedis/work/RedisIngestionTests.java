package org.muks.redis.jedis.work;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.muks.es650.stats.UsageMetrices;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Ref: https://www.baeldung.com/jedis-java-redis-client-library
 */


public class RedisIngestionTests {

  static String offlineDlpPrefix = "offlinedlp";
  static String dlpEventStats = "dlp";
  static int tenantId = 2222;
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


  private static String getTimeNow() {
    return Instant.now().toString();
  }

  public static void main(String[] args) {
    List<String> myList = new ArrayList<>();
    myList.add("muks");
    //myList.add("muk2");
    int size = myList.size();
    System.out.println("size: " + size + ", == " + myList.get(size-1));

    String cloudStatsKey1 = Joiner.on(":").join(offlineDlpPrefix, dlpEventStats, cloudServiceStatsCacheKey);
    String cloudStatsKey2 = Joiner.on(":").join(offlineDlpPrefix, dlpEventStats, cloudServiceStatsCacheKey);

    String cloudStatsKey3 = Joiner.on(":").join(offlineDlpPrefix, dlpEventStats, cloudServiceStatsCacheKey,
        tenantId, cspId, instanceId2);

    String cloudStatsKey4 = Joiner.on(":").join(offlineDlpPrefix, dlpEventStats, cloudServiceStatsCacheKey,
        tenantId2, cspId, instanceId3);

    String cloudStatsKeyPattern = Joiner.on("").join(cloudStatsKey1, "*");
    System.out.println(Instant.now());

    try {
      UsageMetrices stats1 = UsageMetrices.builder()
          .setTimestamp(getTimeNow())
          .setTenantId(tenantId)
          .setInstanceId(instanceId)
          .setCspId(cspId)
          .setApi(1)
          .setCount(5)
          .build();

      UsageMetrices stats2 = UsageMetrices.builder()
          .setTimestamp(getTimeNow())
          .setTenantId(tenantId2)
          .setInstanceId(instanceId3)
          .setCspId(cspId)
          .setApi(2)
          .setCount(59)
          .build();

      UsageMetrices stats3 = UsageMetrices.builder()
          .setTimestamp(getTimeNow())
          .setTenantId(1234)
          .setInstanceId(3678)
          .setCspId(28610)
          .setApi(3)
          .setCount(9)
          .build();

      System.out.println("stats1: " + objectMapper.writeValueAsString(stats1));
      System.out.println("stats2: " + objectMapper.writeValueAsString(stats2));
      System.out.println("\n");

      //Connecting to Redis server on localhost
      redisConnect();

      writeToSet(cloudStatsKey3, stats1);
//      writeToSet(cloudStatsKey4, stats2);
//      writeToSet(cloudStatsKey3, stats3);

      List<String> statsKeysToRead = getUsageStatsCacheKeys(cloudStatsKeyPattern);
      System.out.println("Reading total of " + statsKeysToRead.size() + " Redis keys - " + statsKeysToRead);


      statsKeysToRead.forEach(key -> {
        System.out.println("key: " + key);
        String[] splits = key.split(":");

        int length = splits.length;
        System.out.printf("Len: " + length);
        String tenantInstanceKey = Joiner.on(":").join(splits[length-3], splits[length-2], splits[length-1]);
        System.out.println(tenantInstanceKey);

        //TenantInstanceKey tenantInstanceKey = TenantInstanceKey.create(tenantId, cspId, instanceId);
      });

      System.exit(0);


      List<UsageMetrices> stats = new ArrayList<>();
      fetchStats(statsKeysToRead, stats);

      System.out.println("\n");
      System.out.println("--- final stats size: " + stats.size());
      for (UsageMetrices usageStats : stats) {
        System.out.println(usageStats);
      }
      System.out.println("--- final stats ---");
      /**
       UsageMetrices{timestamp=2022-05-05 19:40:23:343, tenantId=1234, tenantInstanceId=5678, csp=28610, api=1, count=5}
       UsageMetrices{timestamp=2022-05-05 19:40:23:343, tenantId=1234, tenantInstanceId=3678, csp=28610, api=3, count=9}
       UsageMetrices{timestamp=2022-05-05 19:40:23:345, tenantId=2234, tenantInstanceId=2678, csp=28610, api=2, count=59}
       */

      //List<UsageMetrices> stats = fetch(cloudStatsKey3);
      //System.out.println(stats);

//      // ****** HSet examples **********
//
//      //writeToHSet(stats1);
//      writeToHSet(cloudStatsKey1, stats1);
//      writeToHSet(cloudStatsKey2, stats2);
//      writeToHSet(cloudStatsKey1, stats3);
//
//

//      List<UsageMetrices> statsFetched = fetchStats(statsKeysToRead);
//
//      System.out.println("\n");
//      System.out.println("--- final stats size: " + statsFetched.size());
//      for (UsageMetrices usageStats : statsFetched) {
//        System.out.println(usageStats);
//      }
//      System.out.println("--- final stats ---");

      //deleteKey(RedisKey);
      //hDeleteAllByKeys();

    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }


  /**
   * ============== logically methods ==============
   */
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
    JedisClient.hset(key, String.valueOf(data.getTimestamp()), statsAsString);  // ingest into redis in json string format

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
    JedisClient.sadd(key, statsAsString);
//    UnifiedJedis client = new JedisPooled(Protocol.DEFAULT_HOST, 6379);
//    client.jsonSet(key, statsAsString);
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
}

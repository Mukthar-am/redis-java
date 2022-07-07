package org.muks.redis.jedis.work.rollup;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.muks.commons.dao.CloudServiceStatsCache;
import com.muks.commons.dao.UsageMetrices;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;

public class RedisKeyBasedRollup {
  private String host = "127.0.0.1";
  private int port = 6379;
  private Jedis JedisClient = null;

  static int cspId = 25680;
  static int tenantId = 10402;
  static int instanceId = 28119;
  static int api1 = 1;
  static int api2 = 2;
  static int api3 = 3;

  String timestamp1 = "2022-06-01T08:00:00.000Z";
  String timestamp2 = "2022-06-04T23:00:00.000Z";
  int eventCount1 = 3;

  String LDFlag_ApiUsageStatsRollupFrequency = "hourly";
//  String LDFlag_ApiUsageStatsRollupFrequency = "daily";
////  String LDFlag_ApiUsageStatsRollupFrequency = "monthly";
////  String LDFlag_ApiUsageStatsRollupFrequency = "minutes";


  protected final ObjectMapper objectMapper;

  {
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public static void main(String[] args) {

    RedisKeyBasedRollup rollup = new RedisKeyBasedRollup();
    rollup.redisLocalCacheConnect();

    CloudServiceStatsCache cloudServiceStatsCache = new CloudServiceStatsCache();
    cloudServiceStatsCache.setJedisClient(rollup.JedisClient);


    UsageMetrices usageMetrices1 = cloudServiceStatsCache.buildUsageMetricesStats(rollup.timestamp1,
        tenantId, instanceId, cspId,
        api1, rollup.eventCount1);
    cloudServiceStatsCache.registerUsageStatsInRedis(tenantId, cspId, instanceId, usageMetrices1);


    UsageMetrices usageMetrices2 = cloudServiceStatsCache.buildUsageMetricesStats(rollup.timestamp2,
        tenantId, instanceId, cspId,
        api3, rollup.eventCount1);
    cloudServiceStatsCache.registerUsageStatsInRedis(tenantId, cspId, instanceId, usageMetrices2);


  }







  private void redisLocalCacheConnect() {
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

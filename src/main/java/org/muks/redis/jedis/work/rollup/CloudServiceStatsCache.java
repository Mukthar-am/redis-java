//package org.muks.redis.jedis.work.rollup;
//
//
//import com.google.common.base.Joiner;
//import com.muks.commons.dao.UsageMetrices;
//import redis.clients.jedis.Jedis;
//
//import java.time.Instant;
//
//public class CloudServiceStatsCache {
//
//  private Jedis jedisClient = null;
//  private ApiUsageStatsRollupFrequency microsoftTeamsApiStatsFrequency = null;
//
//
//  public void setJedisClient(Jedis client) {
//    this.jedisClient = client;
//  }
//
//  public void setApiUsageStatsFrequency(ApiUsageStatsRollupFrequency frequency) {
//    this.microsoftTeamsApiStatsFrequency = frequency;
//  }
//
//  public UsageMetrices buildUsageMetricesStats(
//      String timestamp,
//      Integer tenantId,
//      Integer tenantInstanceId,
//      Integer csp,
//      Integer apiType,
//      Integer statsCount) {
//
//    return UsageMetrices.builder()
//        .setTimestamp(timestamp) // using provided timestamp value
//        .setTenantId(tenantId)
//        .setInstanceId(tenantInstanceId)
//        .setCspId(csp)
//        .setApi(apiType)
//        .setCount(statsCount)
//        .build();
//  }
//
//  public UsageMetrices buildUsageMetricesStats(
//      Integer tenantId,
//      Integer tenantInstanceId,
//      Integer csp,
//      Integer apiType,
//      Integer statsCount) {
//
//    return UsageMetrices.builder()
//        .setTimestamp(getTimeNow()) // defaulting it to the current time/now() value
//        .setTenantId(tenantId)
//        .setInstanceId(tenantInstanceId)
//        .setCspId(csp)
//        .setApi(apiType)
//        .setCount(statsCount)
//        .build();
//  }
//
//  public void registerUsageStatsInRedis(int tenantId, int cspId, int instanceId, UsageMetrices usageMetrices) {
//    String cloudServiceStatsKey = KeyMaker.makeCloudServiceStatsKey(tenantId, cspId, instanceId);
//    cloudServiceStatsKey = Joiner.on(KeyMaker.SEPARATOR).join(cloudServiceStatsKey, usageMetrices.getApi());
//
//    String dateTimeDelimiter = "T";
//    String timeStampDelimiter = ":";
//    String dateStampDelimiter = "-";
//    String[] dateTime = usageMetrices.getTimestamp().split(dateTimeDelimiter);
//    String statsHashSetKeyName = null;
//
//    ApiUsageStatsRollupFrequency statsRollupFrequency = ApiUsageStatsRollupFrequency.get("hourly");
//
//    if (statsRollupFrequency.equals(ApiUsageStatsRollupFrequency.HOURLY)) {  // should we convert the frequency to ERUM ?
//      cloudServiceStatsKey = Joiner.on(KeyMaker.SEPARATOR).join(cloudServiceStatsKey, dateTime[0]); // yyyy-mm-dd is the redis key suffix
//      statsHashSetKeyName = dateTime[1].split(timeStampDelimiter)[0]; // hh/hour is the rollup hkey
//    }
//    else if (statsRollupFrequency.equals(ApiUsageStatsRollupFrequency.DAILY)) {
//      String[] dateSplits = dateTime[0].split(dateStampDelimiter);
//      String monthPrefix = Joiner.on(dateStampDelimiter).join(dateSplits[0], dateSplits[1]);
//
//      cloudServiceStatsKey = Joiner.on(KeyMaker.SEPARATOR).join(cloudServiceStatsKey, monthPrefix); // yyyy-mm is the redis key suffix
//      statsHashSetKeyName = dateSplits[2];  // dd from yyyy-mm-dd date-stamp is the rollup hkey
//    }
//    else if (statsRollupFrequency.equals(ApiUsageStatsRollupFrequency.MONTHLY)) {
//      String[] dateSplits = dateTime[0].split(dateStampDelimiter);
//      cloudServiceStatsKey = Joiner.on(KeyMaker.SEPARATOR).join(cloudServiceStatsKey, dateSplits[0]); // yyyy is the redis key suffix
//      statsHashSetKeyName = dateSplits[1];  // dd from yyyy-mm-dd date-stamp is the rollup hkey
//    }
//
//    try {
//      if (usageMetrices.getTenantId() == null || usageMetrices.getApi() == null) {
//        System.out.println("Not updating stats to redis cache, found empty usage metrices: " + usageMetrices);
//
//      } else {
//        jedisClient.hincrBy(cloudServiceStatsKey, statsHashSetKeyName, usageMetrices.getCount());
//
//          System.out.println("Successfully ingested usage stats at redis for "
//              + "tenantId: " + usageMetrices.getTenantId() + ", "
//              + "csp: " + CloudServiceProvider.fromDbId(usageMetrices.getCspId()).getDisplayName() + ", "
//              + "api: " + CloudServiceApiType.fromId(usageMetrices.getApi()).getDisplayName() + ", "
//              + "count: " + usageMetrices.getCount()
//              + " at RedisKey: " + cloudServiceStatsKey
//              + ", hKey: " +   statsHashSetKeyName
//          );
//      }
//
//    } catch (Exception e) {
//      System.out.println("Exception occurred while registering stats into redis, reason: " + e);
//    }
//
//  }
//
//  private static String getTimeNow() {
//    return Instant.now().toString();
//  }
//
//}

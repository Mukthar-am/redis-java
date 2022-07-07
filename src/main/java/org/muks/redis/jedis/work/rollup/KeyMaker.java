//package org.muks.redis.jedis.work.rollup;
//
//import com.google.common.base.Joiner;

//
//public class KeyMaker {
//
//  public static final String SEPARATOR = ":";
//  static String OFFLINEDLP_PREFIX = "offlinedlp";
//  static String DLP_EVENT_STAT = "dlp";
//  static String CLOUD_SERVICE_STATS_CACHE = "cloud_service_stats";
//
//  static String makeCloudServiceStatsKey(int tenantId, int cspId, int instanceId) {
//    return Joiner.on(SEPARATOR).join(makeCloudServiceStatsKeyPath(), tenantId, cspId, instanceId);
//  }
//
//  public static String makeCloudServiceStatsKeyPath() {
//    return Joiner.on(SEPARATOR).join(OFFLINEDLP_PREFIX, DLP_EVENT_STAT, CLOUD_SERVICE_STATS_CACHE);
//  }
//
//  public static String getCloudServiceStatsKeySearchPattern() {
//    return Joiner.on("").join(makeCloudServiceStatsKeyPath(), "*");
//  }
//
//}

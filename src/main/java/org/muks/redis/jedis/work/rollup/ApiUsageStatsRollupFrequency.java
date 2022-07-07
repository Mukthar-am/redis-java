//package org.muks.redis.jedis.work.rollup;
//
//
//public enum ApiUsageStatsRollupFrequency {
//  HOURLY("HOURLY"),
//  DAILY("DAILY"),
//  MONTHLY("MONTHLY")
//  ;
//
//  private String rollupFrequency;
//
//  ApiUsageStatsRollupFrequency(String rollupFrequency) {
//    this.rollupFrequency = rollupFrequency;
//  }
//
//  public String getRollupFrequency() {
//    return rollupFrequency;
//  }
//
//  public static ApiUsageStatsRollupFrequency get(String value) {
//    for (ApiUsageStatsRollupFrequency key : ApiUsageStatsRollupFrequency.values()) {
//      if (key.getRollupFrequency().equalsIgnoreCase(value)) {
//        return key;
//      }
//    }
//    return null;
//  }
//}

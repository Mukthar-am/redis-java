//package org.muks.redis.jedis.work.rollup;
//
//public enum CloudServiceApiType {
//  MICROSOFT_TEAMS_CHANGE_NOTIFICATION_API(1, "MSTeamsChangeNotificationAPI"),
//  MICROSOFT_TEAMS_PATCH_API(2, "MSTeamsPatchAPI"),
//  MICROSOFT_TEAMS_EXPORT_API(3, "MSTeamsExportAPI"),
//  UNKNOWN(-1, "Unknown");
//
//  private Integer id;
//  private String displayName;
//
//  CloudServiceApiType(int id, String displayName) {
//    this.id = id;
//    this.displayName = displayName;
//  }
//
//  public int getId() {
//    return id;
//  }
//
//  public String getDisplayName() {
//    return displayName;
//  }
//
//  public static CloudServiceApiType fromId(Integer id) {
//    for (CloudServiceApiType key : CloudServiceApiType.values()) {
//      if (key.getId() == id) {
//        return key;
//      }
//    }
//    return null;
//  }
//}
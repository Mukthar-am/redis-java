//package org.muks.redis.jedis.work.license.dao;
//
//
//import com.google.auto.value.AutoValue;
//import com.google.common.base.Joiner;
//import com.google.common.base.Splitter;
//import com.google.common.base.Strings;
//import com.google.common.collect.Lists;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//
//import java.io.Serializable;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.annotation.Nullable;
//
//@AutoValue
//@JsonDeserialize(builder = AutoValue_TenantServiceKey.Builder.class)
//public abstract class TenantServiceKey implements Serializable {
//  private static final long serialVersionUID = -2567684036289883407L;
//  public static final String USER_ID = "userId";
//  /**
//   *  SuperAdmin is also known as SHN_UNKNOWN_USER
//   */
//  public static final String UNKNOWN = "SHN_UNKNOWN_USER";
//
//  public static TenantServiceKey.Builder builder() {
//    return new AutoValue_TenantServiceKey.Builder();
//  }
//
//  public static TenantServiceKey.Builder builder(TenantServiceKey tenantServiceKey) {
//    return new AutoValue_TenantServiceKey.Builder(tenantServiceKey);
//  }
//
//  TenantServiceKey() {
//  }
//
//  @JsonProperty("tenant_id")
//  public abstract int getTenantId();
//
//  @JsonProperty("csp_id")
//  public abstract int getCspId();
//
//  @Nullable
//  @JsonProperty("instance_id")
//  public abstract Integer getInstanceId();
//
//  @Nullable
//  @JsonProperty("additional_identifiers")
//  public abstract Map<String, String> getAdditionalIdentifiers();
//
//  @AutoValue.Builder
//  public interface Builder {
//
//    @JsonProperty("tenant_id")
//    TenantServiceKey.Builder setTenantId(int tenantId);
//
//    @JsonProperty("csp_id")
//    TenantServiceKey.Builder setCspId(int cspId);
//
//    @JsonProperty("instance_id")
//    TenantServiceKey.Builder setInstanceId(Integer instanceId);
//
//    @JsonProperty("additional_identifiers")
//    TenantServiceKey.Builder setAdditionalIdentifiers(Map<String, String> additionalIdentifiers);
//
//    TenantServiceKey build();
//  }
//
//  public boolean isSuperAdmin() {
//    return UNKNOWN.equalsIgnoreCase(getUserId());
//  }
//
//  public static boolean isSuperAdmin(String userId) {
//    return UNKNOWN.equalsIgnoreCase(userId);
//  }
//
//  @Deprecated
//  public static boolean isUnknownUser(String userId) {
//    return UNKNOWN.equalsIgnoreCase(userId);
//  }
//
//  public String getUserId() {
//    String userId = UNKNOWN;
//    Map<String, String> additionalDetails = this.getAdditionalIdentifiers();
//    if (additionalDetails != null && !additionalDetails.isEmpty() &&
//        !Strings.isNullOrEmpty(additionalDetails.get(USER_ID))) {
//      userId = additionalDetails.get(USER_ID);
//    }
//    return userId;
//  }
//
//  public boolean isUserEmpty() {
//    return UNKNOWN.equals(getUserId());
//  }
//
//  /**
//   * NOTE: The below methods have been copied from TenantCspDbKey to maintain consistency.
//   * Should be refactored
//   **/
//  public static Map<String, String> buildUserAdditionalMap(String userId) {
//    if (userId == null) {
//      userId = UNKNOWN;
//    }
//    Map<String, String> additionalDetails = new HashMap<>();
//    additionalDetails.put(USER_ID, userId);
//    return additionalDetails;
//  }
//
//  public static TenantServiceKey create(int tenantId, int cspId, Integer instanceId,
//      Map<String, String> additionalIdentifiers) {
//    return TenantServiceKey.builder()
//        .setTenantId(tenantId)
//        .setCspId(cspId)
//        .setInstanceId(instanceId)
//        .setAdditionalIdentifiers(additionalIdentifiers)
//        .build();
//  }
//
//
//  public static TenantServiceKey create(int tenantId, int cspId, Integer instanceId,
//      String userId) {
//    return create(tenantId, cspId, instanceId, buildUserAdditionalMap(userId));
//  }
//
//  public static TenantServiceKey create(int tenantId, int cspId, Integer instanceId) {
//    return create(tenantId, cspId, instanceId, (String) null);
//  }
//
//  public static TenantServiceKey create(int tenantId, int cspId, String userId) {
//    return create(tenantId, cspId, null, buildUserAdditionalMap(userId));
//  }
//
//  private static TenantServiceKey create(int tenantId, int cspId, String[] userIdParts) {
//    String userId = createUserId(userIdParts);
//    return create(tenantId, cspId, null, buildUserAdditionalMap(userId));
//  }
//
//  private static TenantServiceKey create(int tenantId, int cspId, Integer instanceId, String[]
//      userIdParts) {
//    String userId = createUserId(userIdParts);
//    return create(tenantId, cspId, instanceId, buildUserAdditionalMap(userId));
//  }
//
//  public static TenantServiceKey create(int tenantId, int cspId) {
//    return create(tenantId, cspId, null, (String) null);
//  }
//
//  public String getKeyWithAdditionalIdentifiers() {
//    return Joiner.on(":").skipNulls().join(
//        Joiner.on(":").skipNulls().join(getTenantId(), getCspId(), getInstanceId()),
//        Joiner.on(":").skipNulls().join(getAdditionalIdentifiers().values()));
//  }
//
//  public String getKey() {
//    return Joiner.on(":").skipNulls().join(this.getTenantId(), this.getCspId(), this
//        .getInstanceId(), this.getUserId());
//  }
//
//  /***  These methods need more thought. ****/
//  public static TenantServiceKey parse(String key) {
//    String[] split = getKeyParts(key);
//    if (split.length < 2) {
//      throw new IllegalArgumentException("Failed to parse " + key);
//    } else if (split.length == 3) {
//      return create(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
//          (String[]) Arrays.copyOfRange(split, 2, split.length));
//    } else {
//      return create(Integer.parseInt(split[0]), Integer.parseInt(split[1]),
//          Integer.parseInt(split[2]), (String[]) Arrays.copyOfRange(split, 3, split.length));
//    }
//  }
//
//  public TenantInstanceKey getTenantInstanceKey() {
//    return TenantInstanceKey.create(getTenantId(), getCspId(), getInstanceId());
//  }
//
//  private static String[] getKeyParts(String key) {
//    Iterable split = Splitter.on(":").split(key);
//    return (String[]) Lists.newArrayList(split).toArray(new String[0]);
//  }
//
//  private static String createUserId(String[] userIdParts) {
//    if (userIdParts.length == 0) {
//      return null;
//    } else {
//      StringBuilder userBuilder = new StringBuilder();
//      userBuilder.append(userIdParts[0]);
//
//      for (int i = 1; i < userIdParts.length; ++i) {
//        userBuilder.append(":");
//        userBuilder.append(userIdParts[i]);
//      }
//
//      return userBuilder.toString();
//    }
//  }
//}

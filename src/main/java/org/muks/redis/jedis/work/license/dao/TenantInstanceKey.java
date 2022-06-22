//package org.muks.redis.jedis.work.license.dao;
//
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.google.auto.value.AutoValue;
//import com.google.common.base.Joiner;
//import com.google.common.base.Preconditions;
//import com.google.common.base.Splitter;
//import java.io.Serializable;
//import java.util.List;
//import java.util.Optional;
//import javax.annotation.Nullable;
//
//@AutoValue
//@JsonDeserialize(builder = AutoValue_TenantInstanceKey.Builder.class)
//public abstract class TenantInstanceKey implements Serializable {
//
//  public static TenantInstanceKey.Builder builder() {
//    return new AutoValue_TenantInstanceKey.Builder();
//  }
//
//  public static TenantInstanceKey.Builder builder(TenantInstanceKey tenantInstanceKey) {
//    return new AutoValue_TenantInstanceKey.Builder(tenantInstanceKey);
//  }
//
//  TenantInstanceKey() {
//  }
//
//  @JsonProperty("tenant_id")
//  @Nullable
//  public abstract Integer getTenantId();
//
//  @JsonProperty("csp_id")
//  @Nullable
//  public abstract Integer getCspId();
//
//  @JsonProperty("instance_id")
//  @Nullable
//  public abstract Integer getInstanceId();
//
//  @JsonProperty("scopes")
//  @Nullable
//  public abstract String getScopes();
//
//  public static TenantInstanceKey create(int tenantId, int cspId) {
//    return create(tenantId, cspId, Optional.<Integer>empty(), Optional.<String>empty());
//  }
//
//  public static TenantInstanceKey create(TenantServiceKey tenantServiceKey) {
//    return create(tenantServiceKey.getTenantId(),
//        tenantServiceKey.getCspId(),
//        tenantServiceKey.getInstanceId());
//  }
//
//  public static TenantInstanceKey create(Integer tenantId, Integer cspId, Integer instanceId) {
//    return AutoValue_TenantInstanceKey.builder().setTenantId(tenantId).setCspId(cspId)
//        .setInstanceId(instanceId).build();
//  }
//
//  public static TenantInstanceKey create(Integer tenantId, Integer cspId, Integer instanceId, String scopes) {
//    return AutoValue_TenantInstanceKey.builder().setTenantId(tenantId).setCspId(cspId)
//        .setInstanceId(instanceId).setScopes(scopes).build();
//  }
//
//  public static TenantInstanceKey create(int tenantId, int cspId, Optional<Integer> instanceId) {
//    return AutoValue_TenantInstanceKey.builder()
//        .setTenantId(tenantId)
//        .setCspId(cspId)
//        .setInstanceId(instanceId != null && instanceId.isPresent() ? instanceId.get() : null)
//        .build();
//  }
//
//  public static TenantInstanceKey create(int tenantId, int cspId, Optional<Integer> instanceId, Optional<String> scopes) {
//    return AutoValue_TenantInstanceKey.builder()
//        .setTenantId(tenantId)
//        .setCspId(cspId)
//        .setInstanceId(instanceId != null && instanceId.isPresent() ? instanceId.get() : null)
//        .setScopes(scopes != null && scopes.isPresent() ? scopes.get() : null)
//        .build();
//  }
//
//  public String key() {
//    return Joiner.on(KeyMaker.SEPARATOR).skipNulls().join(this.getTenantId(), this.getCspId(), this
//        .getInstanceId(), this.getScopes());
//  }
//
//  public static TenantInstanceKey fromKey(String key) {
//    final List<String> tenantInstanceKey = Splitter.on(KeyMaker.SEPARATOR).splitToList(key);
//    Preconditions.checkState(tenantInstanceKey.size() >= 2,
//        "Tenant instance key is invalid " + tenantInstanceKey);
//    if (tenantInstanceKey.size() == 3) {
//      return TenantInstanceKey.create(Integer.valueOf(tenantInstanceKey.get(0)),
//          Integer.valueOf(tenantInstanceKey.get(1)), Integer.valueOf(tenantInstanceKey.get(2)));
//    } else if(tenantInstanceKey.size() == 4) {
//      return TenantInstanceKey.create(Integer.valueOf(tenantInstanceKey.get(0)),
//          Integer.valueOf(tenantInstanceKey.get(1)), Integer.valueOf(tenantInstanceKey.get(2)),
//          tenantInstanceKey.get(3));
//    }
//    return TenantInstanceKey.create(Integer.valueOf(tenantInstanceKey.get(0)),
//        Integer.valueOf(tenantInstanceKey.get(1)));
//  }
//
//  @AutoValue.Builder
//  public interface Builder {
//
//    @JsonProperty("tenant_id")
//    TenantInstanceKey.Builder setTenantId(Integer tenantId);
//
//    @JsonProperty("csp_id")
//    TenantInstanceKey.Builder setCspId(Integer cspId);
//
//    @JsonProperty("instance_id")
//    TenantInstanceKey.Builder setInstanceId(Integer instanceId);
//
//    @JsonProperty("scopes")
//    @Nullable
//    TenantInstanceKey.Builder setScopes(String scopes);
//
//    TenantInstanceKey build();
//
//  }
//}

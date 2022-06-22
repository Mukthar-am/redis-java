package org.muks.redis.jedis.work.license.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;

import java.util.List;


@AutoValue
@JsonDeserialize(builder = AutoValue_Sku.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Sku {

  public static Builder builder() {
    return new AutoValue_Sku.Builder();
  }

  @Nullable
  @JsonProperty("capabilityStatus")
  public abstract String getCapabilityStatus();

  @Nullable
  @JsonProperty("consumedUnits")
  public abstract String getConsumedUnits();

  @Nullable
  @JsonProperty("id")
  public abstract String getId();

  @Nullable
  @JsonProperty("skuId")
  public abstract String getSkuId();

  @Nullable
  @JsonProperty("skuPartNumber")
  public abstract String getSkuPartNumber();

  @Nullable
  @JsonProperty("appliesTo")
  public abstract String getAppliesTo();

  @Nullable
  @JsonProperty("prepaidUnits")
  public abstract PrepaidUnits getPrepaidUnits();

  @Nullable
  @JsonProperty("servicePlans")
  public abstract List<ServicePlan> getServicePlans();


  @AutoValue.Builder
  public interface Builder {
    @JsonProperty("capabilityStatus")
    Builder setCapabilityStatus(String capabilityStatus);

    @JsonProperty("consumedUnits")
    Builder setConsumedUnits(String consumedUnits);

    @JsonProperty("id")
    Builder setId(String id);

    @JsonProperty("skuId")
    Builder setSkuId(String skuId);

    @JsonProperty("skuPartNumber")
    Builder setSkuPartNumber(String skuPartNumber);

    @JsonProperty("appliesTo")
    Builder setAppliesTo(String appliesTo);

    @JsonProperty("prepaidUnits")
    Builder setPrepaidUnits(PrepaidUnits prepaidUnits);

    @JsonProperty("servicePlans")
    Builder setServicePlans(List<ServicePlan> ServicePlans);

    Sku autoBuild();

    default Sku build() {
      return autoBuild();
    }

  }
}

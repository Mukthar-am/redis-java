package org.muks.redis.jedis.work.license.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import com.muks.es650.stats.UsageMetrices;
import javax.annotation.Nullable;


@AutoValue
@JsonDeserialize(builder = AutoValue_ServicePlan.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class ServicePlan {

  public static Builder builder() {
    return new AutoValue_ServicePlan.Builder();
  }

  @Nullable
  @JsonProperty("servicePlanId")
  public abstract String getServicePlanId();

  @Nullable
  @JsonProperty("servicePlanName")
  public abstract String getServicePlanName();

  @Nullable
  @JsonProperty("provisioningStatus")
  public abstract String getProvisioningStatus();

  @Nullable
  @JsonProperty("appliesTo")
  public abstract String getAppliesTo();

  @AutoValue.Builder
  public interface Builder {

    @JsonProperty("servicePlanId")
    Builder setServicePlanId(String capabilityStatus);

    @JsonProperty("servicePlanName")
    Builder setServicePlanName(String consumedUnits);

    @JsonProperty("provisioningStatus")
    Builder setProvisioningStatus(String provisioningStatus);

    @JsonProperty("appliesTo")
    Builder setAppliesTo(String appliesTo);


    ServicePlan autoBuild();

    default ServicePlan build() {
      return autoBuild();
    }

  }

}

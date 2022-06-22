package org.muks.redis.jedis.work.license.dao;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;

@AutoValue
@JsonDeserialize(builder = AutoValue_PrepaidUnits.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PrepaidUnits {

  public static Builder builder() {
    return new AutoValue_PrepaidUnits.Builder();
  }

  @Nullable
  @JsonProperty("enabled")
  public abstract String getEnabled();

  @Nullable
  @JsonProperty("suspended")
  public abstract String getSuspended();

  @Nullable
  @JsonProperty("warning")
  public abstract String getWarning();


  @AutoValue.Builder
  public interface Builder {

    @JsonProperty("enabled")
    Builder setEnabled(String enabled);

    @JsonProperty("suspended")
    Builder setSuspended(String suspended);

    @JsonProperty("warning")
    Builder setWarning(String warning);

    PrepaidUnits autoBuild();

    default PrepaidUnits build() {
      return autoBuild();
    }
  }

}

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

  @JsonProperty("enabled")
  public abstract int getEnabled();

  @JsonProperty("suspended")
  public abstract int getSuspended();

  @JsonProperty("warning")
  public abstract int getWarning();


  @AutoValue.Builder
  public interface Builder {

    @JsonProperty("enabled")
    Builder setEnabled(int enabled);

    @JsonProperty("suspended")
    Builder setSuspended(int suspended);

    @JsonProperty("warning")
    Builder setWarning(int warning);

    PrepaidUnits autoBuild();

    default PrepaidUnits build() {
      return autoBuild();
    }
  }

}

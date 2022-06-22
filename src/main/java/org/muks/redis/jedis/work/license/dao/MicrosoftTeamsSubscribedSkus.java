package org.muks.redis.jedis.work.license.dao;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.auto.value.AutoValue;
import javax.annotation.Nullable;

import java.util.List;

@AutoValue
@JsonDeserialize(builder = AutoValue_MicrosoftTeamsSubscribedSkus.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class MicrosoftTeamsSubscribedSkus {

  public static Builder builder() {
    return new AutoValue_MicrosoftTeamsSubscribedSkus.Builder();
  }

  @Nullable
  @JsonProperty("value")
  public abstract List<Sku> getValue();


  @AutoValue.Builder
  public interface Builder {

    @JsonProperty("value")
    Builder setValue(List<Sku> value);

    MicrosoftTeamsSubscribedSkus autoBuild();

    default MicrosoftTeamsSubscribedSkus build() {
      return autoBuild();
    }
  }
}

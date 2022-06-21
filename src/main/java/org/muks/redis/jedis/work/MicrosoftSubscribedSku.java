package org.muks.redis.jedis.work;

import org.muks.redis.jedis.work.dao.SKU;

import java.util.List;

public class MicrosoftSubscribedSku {
  List<SKU> values;

  public List<SKU> getValues() {
    return values;
  }

  public void setValues(List<SKU> values) {
    this.values = values;
  }
}

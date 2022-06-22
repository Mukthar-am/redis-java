package org.muks.redis.jedis.work.license.license;

import com.google.common.base.Joiner;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;

import java.time.Instant;
import java.util.Map;


public class TenantCspUserLicenseIngestion {
  int cspId = 25860;
  int tenantId = 10402;
  int instanceId = 28119;
  private static String host = "127.0.0.1";
  private static int port = 6379;
  private static Jedis JedisClient = null;

  public static void main(String[] args) {

    //TenantInstanceKey tenantInstanceKey = TenantInstanceKey.create(25860, 10402,28119);

    TenantCspUserLicenseIngestion tenantCspUserLicenseIngestion = new TenantCspUserLicenseIngestion();
    tenantCspUserLicenseIngestion.redisLocalCacheConnect();
    String key = tenantCspUserLicenseIngestion.makeTenantCspUserLicenseKey();
    System.out.println("key: " + key);

    tenantCspUserLicenseIngestion.persistUserLicensesData(key, 125);

    System.out.println("getTenantCspUserLicenseCount: " + tenantCspUserLicenseIngestion.getTenantCspUserLicenseCount(key));

  }

  public void persistUserLicensesData(String key, int userLicenseCount) {
    JedisClient.del(key);
    JedisClient.hset(key, String.valueOf(Instant.now()), String.valueOf(userLicenseCount));
  }

  public String getTenantCspUserLicenseCount(String key) {
    return (String) JedisClient.hgetAll(key).values().toArray()[0];
  }

  public String makeTenantCspUserLicenseKey() {
    String keyRoot = "offlinedlp:dlp:license";
    return Joiner.on(":").join(keyRoot, cspId, tenantId, instanceId);
  }

  private void redisLocalCacheConnect() {
    boolean useSsl = false;
    // Connect to the Azure Cache for Redis over the TLS/SSL port using the key.
    JedisClient = new Jedis(host, port, DefaultJedisClientConfig.builder()
        .ssl(useSsl)
        .build());

    // Simple PING command
    System.out.println( "\nCache Command  : Ping" );
    System.out.println( "Cache Response : " + JedisClient.ping());
  }
}

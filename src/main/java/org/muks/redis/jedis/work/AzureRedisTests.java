package org.muks.redis.jedis.work;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// https://www.baeldung.com/spring-data-redis-tutorial

// https://github.com/MicrosoftDocs/azure-docs/blob/main/articles/azure-cache-for-redis/cache-java-get-started.md

public class AzureRedisTests {
  private static String host = "shndlpredis.redis.cache.windows.net";
  private static int port = 6380;
  private static String passwd = "CnU3xL5OLYpclq350Bd3LaGAIouxl5M6NEPaN0pSneM=";
  private static Jedis jedis = null;

  public static void main(String[] args) {
    AzureRedisTests azureRedisTests = new AzureRedisTests();
    azureRedisTests.azureRedisCacheConnect();
    azureRedisTests.azureRedisCacheConnClose();

  }


  public List<String> azureRedisCacheConnect() {
    boolean useSsl = true;
    // Connect to the Azure Cache for Redis over the TLS/SSL port using the key.
    jedis = new Jedis(host, port, DefaultJedisClientConfig.builder()
        .password(passwd)
        .ssl(useSsl)
        .build());

    // Simple PING command
    System.out.println( "\nCache Command  : Ping" );
    System.out.println( "Cache Response : " + jedis.ping());

    List<String> azureRedosDomains = getAzureWebappDomains();
    System.out.println("total domains from Azure Redis: " + azureRedosDomains.size());

    return azureRedosDomains;

  }

  private List<String> getAzureWebappDomains() {
    String domainsRedisKey = "offlinedlp:domains:spop-sjc";

    boolean domainsKeyExists = jedis.exists(domainsRedisKey);

    List<String> office365Domains = new ArrayList<>();
    if (domainsKeyExists) {
      Map<String, String> domains = jedis.hgetAll(domainsRedisKey);
      office365Domains.addAll(domains.keySet());
    }

    System.out.println("Office365 Domains: " + office365Domains);
    return office365Domains;
  }

  public void azureRedisCacheConnClose() {
    jedis.close();
  }


}

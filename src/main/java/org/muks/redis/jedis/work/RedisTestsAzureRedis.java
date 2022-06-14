package org.muks.redis.jedis.work;

import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.Jedis;

import java.util.Set;


// https://www.baeldung.com/spring-data-redis-tutorial

// https://github.com/MicrosoftDocs/azure-docs/blob/main/articles/azure-cache-for-redis/cache-java-get-started.md

public class RedisTestsAzureRedis {
  private static String host = "shndlpredis.redis.cache.windows.net";
  private static int port = 6380;
  private static String passwd = "CnU3xL5OLYpclq350Bd3LaGAIouxl5M6NEPaN0pSneM=";
  private static Jedis jedis = null;

  public static void main(String[] args) {
    azureRedisCacheConnect();
    azureRedisCacheConnClose();

  }


  private static void azureRedisCacheConnect() {
    boolean useSsl = true;
    // Connect to the Azure Cache for Redis over the TLS/SSL port using the key.
    jedis = new Jedis(host, port, DefaultJedisClientConfig.builder()
        .password(passwd)
        .ssl(useSsl)
        .build());

    // Simple PING command
    System.out.println( "\nCache Command  : Ping" );
    System.out.println( "Cache Response : " + jedis.ping());

    Set<String> keys = jedis.keys("*");
    System.out.println("keys: " + keys.toString());
  }


  private static void azureRedisCacheConnClose() {
    jedis.close();
  }


}

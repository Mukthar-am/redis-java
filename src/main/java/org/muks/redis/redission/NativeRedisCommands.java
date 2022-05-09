package org.muks.redis.redission;

import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.redisson.client.RedisClientConfig;
import org.redisson.client.RedisConnection;
import org.redisson.client.codec.StringCodec;
import org.redisson.client.protocol.RedisCommands;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

public class NativeRedisCommands {
    private static String CONF_DIR = "/Users/mmaniyar/Data/code/git/personal/redission-java/conf";
    private static String CONF_FILE = CONF_DIR + "/" + "singleNodeConfig.json";
    RedissonClient client = null;

    public static void main(String[] args) {
        RedisClientConfig redisClientConfig = new RedisClientConfig();
        redisClientConfig.setAddress("localhost", 6379);

        RedisClient client = RedisClient.create(redisClientConfig);

        RedisConnection conn = client.connect();
        conn.sync(StringCodec.INSTANCE, RedisCommands.SET, "test", 0);

        conn.closeAsync();
        client.shutdown();

    }

}

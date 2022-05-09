package org.muks.redis.redission;

import com.google.common.base.Joiner;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

public class MyRedisClient {
    private String CONF_FILE = null;
    private RedissonClient CLIENT = null;
    private Config CONFIG = null;


    public MyRedisClient setConfigFile(String file) {
        this.CONF_FILE = file;
        return this;
    }

    public MyRedisClient loadConfig(String configFilePath) throws IOException {
        this.CONFIG = Config.fromJSON(new File(configFilePath));
        return this;
    }

    public MyRedisClient loadConfig() throws IOException {
        this.CONFIG = Config.fromJSON(new File(this.CONF_FILE));
        return this;
    }

    public RedissonClient getClientInstance() throws IOException {
        return Redisson.create(this.CONFIG);
    }

    public void close() {
        this.CLIENT.shutdown();
    }

    public static String makeUniqueEventSetKey(String eventKey) {
        String SEPARATOR = ":";
        String UNIQUE_ID = "unique_ids";

        return Joiner.on(SEPARATOR).join(UNIQUE_ID, eventKey);
    }

    public static void main(String[] args) {
        String eventKey = "dlp.file_events";
        System.out.println("uniq key: " + makeUniqueEventSetKey(eventKey));
    }


}

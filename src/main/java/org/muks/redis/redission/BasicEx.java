package org.muks.redis.redission;

import org.redisson.api.RKeys;
import org.redisson.api.RedissonClient;

import java.io.IOException;

public class BasicEx {
    private String CONF_DIR = "/Users/mmaniyar/Data/code/git/personal/redission-java/conf";
    private String CONF_FILE = CONF_DIR + "/" + "singleNodeConfig.json";
    RedissonClient CLIENT = null;


    public static void main(String[] args) {
        BasicEx basicEx = new BasicEx();
        try {
            basicEx.run();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void run() throws IOException {
        try {
            this.CLIENT =
                    new MyRedisClient()
                            .setConfigFile(this.CONF_FILE)
                            .loadConfig()
                            .getClientInstance();


            RKeys keys = this.CLIENT.getKeys();
            System.out.println("Keys: " + keys.toString());

            Iterable<String> allKeys = keys.getKeys();
            Iterable<String> keysByPattern = keys.getKeysByPattern("key*");
            for (String k : allKeys) {
                System.out.println("key=" + k);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            this.CLIENT.shutdown();

        }

    }


//    public void publish() {
//        RTopic publishTopic = client.getTopic("baeldung");
//        long clientsReceivedMessage
//                = publishTopic.publish(new CustomMessage("This is a message"));
//    }


}


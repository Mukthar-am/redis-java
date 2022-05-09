//package org.muks.redis;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//
//import org.redisson.Redisson;
//import org.redisson.api.RMap;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.Config;
//
///**
// * Hello world!
// */
//public class MapEx {
////    public static void main(String[] args) throws IOException {
////        Config config = new Config();
////        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
////
////        RedissonClient client = Redisson.create(config);
////    }
//
//    public static void main(String[] args) throws IOException {
//        // connects to 127.0.0.1:6379 by default
//        RedissonClient redisson = Redisson.create();
//
//        RMap map =  redisson.getMap("myMap");
//        map.put("a", 1);
//        map.put("b", 2);
//        map.put("c", 3);
//
//        boolean contains = map.containsKey("a");
//
//        Integer value = map.get("c");
//        Integer updatedValue = map.addAndGet("a", 32);
//
//        Integer valueSize = map.valueSize("c");
//
//        Set keys = new HashSet();
//        keys.add("a");
//        keys.add("b");
//        keys.add("c");
//        Map mapSlice = map.getAll(keys);
//
//        // use read* methods to fetch all objects
//        Set allKeys = map.readAllKeySet();
//        Collection allValues = map.readAllValues();
//        Set> allEntries = map.readAllEntrySet();
//
//        // use fast* methods when previous value is not required
//        boolean isNewKey = map.fastPut("a", 100);
//        boolean isNewKeyPut = map.fastPutIfAbsent("d", 33);
//        long removedAmount = map.fastRemove("b");
//
//        redisson.shutdown();
//    }
//
//}

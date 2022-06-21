package org.muks.redis.jedis.work;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


// Link : https://www.baeldung.com/jsonobject-iteration


public class JsonUtils {
  static void findInstanceType(Object inst) {
    if (inst instanceof JSONObject) {
      System.out.println("JsonObject");
    } else if (inst instanceof JSONArray) {
      System.out.println("JSONArray..");
    } else {
      System.out.println("unknown");
    }
  }

  private static boolean contains(List<Integer> list, int value) {
    return list.stream().anyMatch(e -> Objects.equals(e, value));
  }

  static void check() {
    List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
    int value = 3;

//    boolean isExists = CollectionUtils.containsAny(list, value);
//    System.out.println(isExists);

    System.out.println("Check: " + contains(list, value));

    boolean isExists = new HashSet<>(list).contains(value);
    System.out.println(isExists);
  }
}

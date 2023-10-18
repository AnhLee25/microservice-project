package com.shop.paymentservice;

import org.springframework.aop.scope.ScopedProxyUtils;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
  public static void main(String[] args) {
    List<String> list = List.of("A", "B", "C", "D", "A", "B", "A");
    Map<String, Integer> map = new HashMap<>();
    list.forEach(
        character -> {
          int count = 0;
          if (map.containsKey(character)) {
            count = map.get(character);
          }
          map.put(character, ++count);
        });
    for (String key : map.keySet()) {
      System.out.println(key + ": " + map.get(key));
    }

    int[] numberList = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    for (int i = 0; i < numberList.length; i++) {}
  }
}

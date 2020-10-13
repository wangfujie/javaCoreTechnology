package com.example.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author wangfj_tongtech
 * @DATE 2020/9/1 11:27
 */
public class TestHashMap {
    private static ConcurrentHashMap<String, String> queueMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        queueMap.put("aa", "bb");
        String value = queueMap.remove("aa");
        System.out.println(value);
    }
}

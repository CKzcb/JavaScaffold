package com.ckzcb.ape.common.guava.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @ClassName CacheUtil
 * @Description
 * @Author 蛋白质先生
 * @Date 2025/5/29 17:36
 * @Version 1.0
 */
public class CacheUtil<K, V> {
    private LoadingCache<K, V> cache = CacheBuilder.newBuilder()
            .maximumSize(1000)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .removalListener(new RemovalListener<K, V>() {
                @Override
                public void onRemoval(com.google.common.cache.RemovalNotification<K, V> notification) {
                    System.out.println("remove......k:" + notification.getKey() + ", v" + notification.getValue());
                }
            })
            .build(new CacheLoader<K, V>() {
                @Override
                public V load(K key) {
                    System.out.println(">>>>>>>>load>>>>>>> key: " + key);
                    return null;
                }
            });

    public boolean rmCache(K key) {
        cache.invalidate(key);
        return true;
    }

    public boolean putCache(K key, V value) {
        cache.put(key, value);
        return true;
    }

    public V getCache(K key) throws ExecutionException {
        return cache.get(key);
    }

    public V getCache(K key, boolean isUpdate) {
        return cache.getIfPresent(key);
    }

    public V getCache(K key, Function<K, V> function) {
        V v = cache.getIfPresent(key);
        if (v == null) {
            v = function.apply(key);
            cache.put(key, v);
        }
        return v;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CacheUtil<String, String> cacheUtil = new CacheUtil<>();
//        cacheUtil.getCache("1");
        cacheUtil.putCache("1", "1");
        System.out.println(cacheUtil.getCache("1", true));
        Thread.sleep(2000);
        cacheUtil.rmCache("1");
        System.out.println(cacheUtil.getCache("1", true));
        Thread.sleep(5000);
        System.out.println(cacheUtil.getCache("1", true));
        Thread.sleep(10000);
        System.out.println(cacheUtil.getCache("1", true));
        System.out.println(cacheUtil.getCache("222", key -> "ggggggggggg"));
    }
}

package com.myproject.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public class JedisDemo {

//    public static void main(String[] args) {
//
//        String host = "192.168.197.128";
//        int port = 6379;
//
//        Jedis jedis = new Jedis(host, port);
//        jedis.auth("aspirine");
//
//        System.out.println(jedis.get("a"));
//
//    }

    private Jedis jedis;

    //@Before
    public void jedisBefore() {
        String host = "192.168.197.128";
        int port = 6301;
        jedis = new Jedis(host, port);
//        jedis.auth("aspirine");

    }

    //@After
    public void jedisAfter() {
        jedis.close();

    }

    @Test
    public void testStr() {
        String key = "applicationName";
        if (jedis.exists(key)) {
            System.out.println("Redis库中的数据为:" + jedis.get(key));
        } else {
            String result = "测试JedisDemo";
            jedis.set(key, result);
            System.out.println("Mysql库中的数据为:" + result);
        }
    }

    @Test
    public void testJedisPoolStr() {

        //JedisConnectionFactory

        RedisTemplate redisTemplate = new RedisTemplate();
        jedis = JedisPoolDemoUtils.getJedis();
        String key = "poolName";
        if (jedis.exists(key)) {
            System.out.println("Redis库中的数据为:" + jedis.get(key));
        } else {
            String result = "测试JedisDemo";
            jedis.set(key, result);
            System.out.println("Mysql库中的数据为:" + result);
        }
        JedisPoolDemoUtils.close(jedis);
    }

    @Test
    public void testSpringJedisPoolStr() {

        //JedisConnectionFactory
        jedis = JedisPoolDemoUtils.getJedis();
        String key = "poolName";
        if (jedis.exists(key)) {
            System.out.println("Redis库中的数据为:" + jedis.get(key));
        } else {
            String result = "测试JedisDemo";
            jedis.set(key, result);
            System.out.println("Mysql库中的数据为:" + result);
        }

        JedisPoolDemoUtils.close(jedis);
    }

    @Test
    public void testSpringJedisPoolClusterAdd() throws IOException {
        Set<HostAndPort> set = new HashSet<HostAndPort>();

        set.add(new HostAndPort("192.168.197.128", 6301));
        set.add(new HostAndPort("192.168.197.128", 6302));
        set.add(new HostAndPort("192.168.197.128", 6303));
        set.add(new HostAndPort("192.168.197.128", 6304));
        set.add(new HostAndPort("192.168.197.128", 6305));
        set.add(new HostAndPort("192.168.197.128", 6306));

        JedisCluster cluster = new JedisCluster(set);
        cluster.set("clusterTest", "测试");
        cluster.set("clusterTest2", "测试2","nx","ex",60);

        cluster.close();

    }



    @Test
    public void testSpringJedisPoolCluster() throws IOException {

        Set<HostAndPort> set = new HashSet<HostAndPort>();

        set.add(new HostAndPort("192.168.197.128", 6301));
        set.add(new HostAndPort("192.168.197.128", 6302));
        set.add(new HostAndPort("192.168.197.128", 6303));
        set.add(new HostAndPort("192.168.197.128", 6304));
        set.add(new HostAndPort("192.168.197.128", 6305));
        set.add(new HostAndPort("192.168.197.128", 6306));

        JedisCluster cluster = new JedisCluster(set);
        String value = cluster.get("clusterTest");

        System.out.println(value);

        cluster.close();
    }

    @Test
    @Cacheable
    public void testSpringJedisPoolCluster2() {

        System.out.println(Runtime.getRuntime().availableProcessors());

        Map map = new WeakHashMap();

        Integer i = new Integer(1);
        Integer k = new Integer(2);

        map.put(i,"22");
        map.put(k,"33");

        i = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);


        WeakReference<Integer> reference = new WeakReference<>(k);

        System.out.println(reference.get());
        k = null;
        System.gc();
        System.out.println(reference.get());

    }



}

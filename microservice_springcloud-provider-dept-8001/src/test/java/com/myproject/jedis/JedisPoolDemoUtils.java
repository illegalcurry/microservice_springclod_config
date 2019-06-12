package com.myproject.jedis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolDemoUtils {

    private static JedisPool jedisPool;

    static {
        String host = "192.168.197.128";
        int port = 6379;
        String password = "aspirine";
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10); //最大连接数
        jedisPoolConfig.setMinIdle(1); //最小存活数
        jedisPool = new JedisPool(jedisPoolConfig, host, port,2000,password);

    }

    public static Jedis getJedis() {
        return jedisPool.getResource();
    }

    public static void close(Jedis jedis) {
        jedis.close();
    }

}

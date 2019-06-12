package com.myproject.jedis;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.myproject.springcloud.DeptApplication8001;
import com.myproject.springcloud.entity.Dept;
import lombok.ToString;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@ToString
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DeptApplication8001.class)
public class JedisSpringDemo {

    @Autowired
    @Qualifier("myRedisTemplate")
    RedisTemplate redisTemplate;

    @Test
    public void testSpringJedisPoolStr() {
        String key = "appName";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            String value = valueOperations.get(key);
            System.out.println("从redis中获取的数据：" + value);
            redisTemplate.expire(key, 2, TimeUnit.HOURS);

        } else {
            String value = "springboot与redis的结合";
            valueOperations.set(key, value);
            System.out.println("从mysql中获取的数据：" + value);

        }

    }

    @Test
    public void testSpringJedisPoolHash() {
        String key = Dept.class.getSimpleName();
        long id = 9;
        HashOperations<String, Long, Object> hashOperations = redisTemplate.opsForHash();
        if (hashOperations.hasKey(key,id)) {

            Map dept = (Map) hashOperations.get(key, id);

            Gson gson = new Gson();
            Dept dept1 = gson.fromJson(dept.toString(),Dept.class);

            System.out.println("从redis中获取的数据：" + dept1.toString());
            redisTemplate.expire(key, 2, TimeUnit.HOURS);

        } else {

            Dept dept = new Dept().setDno(id).setDName("纪律部").setDbSource("db3");

            hashOperations.put(key, id, dept);

            System.out.println("从mysql中获取的数据：" + dept.toString());

        }

    }

    @Test
    public void testSpringJedisPoolListPage() {

        int pageNum = 3;
        int pageSize = 3;

        String key = "l1";
        ListOperations<String, String> list =  redisTemplate.opsForList();

        int start = (pageNum - 1) * pageSize;
        int end = pageNum*pageSize-1;

        List<String> result = list.range(key,start,end);

        result.forEach((string)->{
            System.out.println(string);
        });

    }

    @Test
    public void testSpringJedisPoolCluster() {
        String key = "clusterTest";
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            String value = valueOperations.get(key);
            System.out.println("从redis中获取的数据：" + value);
            redisTemplate.expire(key, 2, TimeUnit.HOURS);

        } else {
            String value = "springboot与redis的结合";
            valueOperations.set(key, value);
            System.out.println("从mysql中获取的数据：" + value);

        }
    }





}

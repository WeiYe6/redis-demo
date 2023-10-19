package com.fengye.redis;

import com.alibaba.fastjson.JSON;
import com.fengye.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class StringRedisTemplateTests {


    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    void testString() {
        //写入一条String数据
        redisTemplate.opsForValue().set("name", "曾伟业");

        //获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    //JSON工具
    //private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testSaveUser() {
        //准备对象
        User user = new User("枫业", 18);
        //手动序列化:将对象转换为Json字符串
        String jsonString = JSON.toJSONString(user);
        //写入一条数据到redis
        redisTemplate.opsForValue().set("user:100",jsonString);

        //读取数据
        String s = redisTemplate.opsForValue().get("user:100");
        //反序列化
        User user2 = JSON.parseObject(s, User.class);
        System.out.println("user2 = " + user2);

    }

    @Test
    void testHash(){
        redisTemplate.opsForHash().put("user:200","name","枫业");
        redisTemplate.opsForHash().put("user:200","age","20");

        Map<Object, Object> entries = redisTemplate.opsForHash().entries("user:200");
        System.out.println("entries = " + entries);
    }

}

package com.fengye.redis;

import com.fengye.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class RedisDemoApplicationTests {


   /* @Autowired
    private RedisTemplate redisTemplate;*/
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void testString() {
        //写入一条String数据
        redisTemplate.opsForValue().set("name", "曾伟业");

        //获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testSaveUser(){
        //写入数据
        redisTemplate.opsForValue().set("user:10",new User("枫业",18));

        //读取数据
        User user = (User) redisTemplate.opsForValue().get("user:10");
        System.out.println("user = " + user);
    }

    @Test
    void testGit(){
       System.out.println("hello-idea");

        System.out.println("hello-github");
    }
}

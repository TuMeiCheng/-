package com.wandeyun.wuyi.website;

import com.wandeyun.wuyi.website.bean.UserInfo;
import com.wandeyun.wuyi.website.mapper.UserInfoMapper;
import org.apache.catalina.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestRedis {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    //mybatis相关测试
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Test
    public void test() throws Exception {
        stringRedisTemplate.opsForValue().set("jjk", "111333");
        Assert.assertEquals("111333", stringRedisTemplate.opsForValue().get("jjk"));
    }

    @Test
    public void testObj() throws Exception {
        UserInfo user = new UserInfo();
        ValueOperations<String, UserInfo> operations = redisTemplate.opsForValue();
        user.setUserName("涂米2");
        user.setStatus(2);
        //保存对象
        operations.set("user111.com.ht", user);
        //缓存对象，设置过期时间，时间类型
        operations.set("com.neo.f", user,60, TimeUnit.SECONDS);
        Thread.sleep(1000);
        //redisTemplate.delete("com.neo.f");
        boolean exists=redisTemplate.hasKey("user111.com.ht");
        Set set1 = new HashSet();
        redisTemplate.boundZSetOps(set1);
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
        // Assert.assertEquals("aa", operations.get("com.neo.f").getUserName());
    }

    @Test
    public  void SelectTest()throws Exception {
            List<UserInfo> userInfoList =  userInfoMapper.getAll();
        for (UserInfo userInfo : userInfoList) {
            System.out.println(userInfo.toString());
        }
        UserInfo uu  = userInfoMapper.getById(2);
        System.out.println("oooUUUUU:"+uu.toString());

        UserInfo us = new UserInfo();
        us.setStatus(2);
        us.setUserName("林俊杰2");
        us.setPhone("1232324434");
        us.setEmail("www.3e@sgl.com");
        us.setContent("都有日倒下");
        userInfoMapper.insert(us);
    }


}

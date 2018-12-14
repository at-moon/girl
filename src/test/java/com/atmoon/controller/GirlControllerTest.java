package com.atmoon.controller;

import com.atmoon.pojo.Girl;
import com.atmoon.utils.RabbitMQUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GirlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RabbitMQUtil rabbitMQUtil;

    @Test
    public void girlList() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/girls"))
            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andExpect(MockMvcResultMatchers.content().string("hh"))
        ;
    }

    @Test
    public void saveGirl() throws Exception {
        ValueOperations<String, Girl> operations = redisTemplate.opsForValue();

        Girl girl = new Girl("wuhan",19);
        operations.set(girl.getCity() , girl);

        girl = new Girl("xiamen",21);
        operations.set(girl.getCity() , girl);

        Assert.assertEquals(19 , operations.get("wuhan").getAge().longValue());
        Assert.assertEquals(21 , operations.get("xiamen").getAge().longValue());
    }

    @Test
    public void sendGirl() {
        Girl girl = new Girl("wuhan",19);
        rabbitMQUtil.sendGirl(girl);
    }
}
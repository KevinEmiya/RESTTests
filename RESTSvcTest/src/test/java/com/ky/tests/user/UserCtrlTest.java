package com.ky.tests.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ky.tests.main.SvcMain;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SvcMain.class)
@TestPropertySource(locations = "classpath:conf/test.properties")
@WebAppConfiguration
public class UserCtrlTest
{
    private MockMvc mvc;
    private static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    
    @Before
    public void setup()
    {
        mvc = MockMvcBuilders.standaloneSetup(new UserCtrl()).build();
    }
    
    @Test
    public void updateUserTest() throws Exception
    {
        String name = "kevin";
        String idcard = "420102199901011234";
        String tel = "13700000000";
        
        User user = new User();
        user.setName(name);
        user.setIdcard(idcard);
        user.setTel(tel);
        String userJson = GSON.toJson(user);
        String uri = "/user/" + name;
        
        RequestBuilder request = MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
                        .content(userJson.getBytes());
        MvcResult result = mvc.perform(request).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals("执行失败", UserCtrl.RET_SUCCESS, content);
        
        request = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
        result = mvc.perform(request).andReturn();
        content = result.getResponse().getContentAsString();
        Assert.assertEquals("获取信息和存入信息不一致", userJson, content);
        
        request = MockMvcRequestBuilders.delete(uri);
        result = mvc.perform(request).andReturn();
        content = result.getResponse().getContentAsString();
        Assert.assertEquals("执行失败", UserCtrl.RET_SUCCESS, content);
        
        request = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
        result = mvc.perform(request).andReturn();
        content = result.getResponse().getContentAsString();
        Assert.assertEquals("获取信息和存入信息不一致", "", content);
    }
}

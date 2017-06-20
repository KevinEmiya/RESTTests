package com.ky.tests.user;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/user")
class UserCtrl
{
    static final Log LOG = LogFactory.getLog(UserCtrl.class);
    public static final String RET_SUCCESS = "success";
    
    Map<String, User> m_cachedUsers = Collections.synchronizedMap(new HashMap<String, User>());
    
    @RequestMapping(path = "test")
    public @ResponseBody String test() throws Exception
    {
        throw new Exception("强行抛一个异常");
    }
    
    @RequestMapping(path = "/{name}", method = RequestMethod.PUT)
    public @ResponseBody String updateUser(@PathVariable String name, @RequestBody User user) throws Exception
    {
        if(name == null || !name.equals(user.getName()))
        {
            throw new Exception("更新用户参数错误！");
        }
        
        User cachedUser = m_cachedUsers.get(name);
        if(cachedUser != null)
        {
            LOG.info("用户已存在，更新用户信息...");
            if(user.idcard != null)
            {
                cachedUser.setIdcard(user.idcard);
            }
            if(user.tel != null)
            {
                cachedUser.setTel(user.tel);
            }
            m_cachedUsers.put(name, cachedUser);
        }
        else
        {
            LOG.info("缓存中无该用户信息，创建新用户...");
            m_cachedUsers.put(name, user);
        }
        
        return RET_SUCCESS;
    }
    
    @RequestMapping(path = "/{name}", method = RequestMethod.GET)
    public @ResponseBody User queryUser(@PathVariable String name)
    {
        return m_cachedUsers.get(name);
    }
    
    @RequestMapping(path = "/{name}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteUser(@PathVariable String name)
    {
        User removedUser = m_cachedUsers.remove(name);
        LOG.info("删除用户: " + removedUser.getName());
        return RET_SUCCESS;
    }
}

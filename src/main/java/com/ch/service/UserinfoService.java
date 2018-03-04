package com.ch.service;

import com.ch.dao.UserinfoDAO;
import com.ch.model.Userinfo;
import com.ch.utils.HifriendsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by apple on 2018/2/28.
 */
@Service
public class UserinfoService {
    @Autowired
    private UserinfoDAO userinfoDAO;

    public Map<String,Object> login(String username,String password){
        Map<String,Object> map = new HashMap<>();
        Userinfo userinfo = userinfoDAO.selectByUsername(username);
        if (userinfo != null){
            if (userinfo.getPassword().equals(password)){
                map.put("token", HifriendsUtil.MD5(UUID.randomUUID().toString().substring(3,7)+userinfo.getId()));
                map.put("nickname", userinfo.getNickname());
            }else {
                map.put("msg","密码错误");
            }
        }else {
            map.put("msg","用户不存在");
        }
        return map;
    }

}

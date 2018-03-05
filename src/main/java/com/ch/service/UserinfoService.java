package com.ch.service;

import com.ch.controller.UserinfoController;
import com.ch.dao.TokenDAO;
import com.ch.dao.UserinfoDAO;
import com.ch.model.Token;
import com.ch.model.Userinfo;
import com.ch.utils.HifriendsUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by apple on 2018/2/28.
 */
@Service
public class UserinfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserinfoService.class);

    @Autowired
    private TokenDAO tokenDAO;

    @Autowired
    private UserinfoDAO userinfoDAO;

    public Map<String,Object> login(String username,String password){
        Map<String,Object> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg","用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)){
            map.put("msg","密码不能为空");
            return map;
        }
        Userinfo userinfo = userinfoDAO.selectByUsername(username);
        if (userinfo == null){
            map.put("msg","用户名不存在");
            return map;
        }

        if(!HifriendsUtil.MD5(password+userinfo.getSalt()).equals(userinfo.getPassword())){
            map.put("msg","密码错误");
            return map;
        }

        String ticket = addToken(userinfo.getId());
        map.put("ticket",ticket);
        map.put("userId",userinfo.getId());
        map.put("nickname",userinfo.getNickname());
        return map;
    }

    public Map<String,Object> getUserinfo(int id){
        Map<String,Object>map = new HashMap<>();
        Userinfo userinfo = userinfoDAO.selectById(id);
        if (userinfo != null){
             map.put("userinfo",userinfo);
        }else {
            map.put("msg","无此用户");
        }
        return map;
    }

    public Map<String,String>regist(String username,String password,String nickname) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isBlank(username)) {
            map.put("msg", "用户名不能为空");
            return map;
        }
        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        Userinfo user = userinfoDAO.selectByUsername(username);
        if (user != null) {
            map.put("msg", "用户名已经被注册");
        }
        user = new Userinfo();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setHeadUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setPassword(HifriendsUtil.MD5(password + user.getSalt()));
        user.setStatus(1);
        user.setCreateTime(new Date());
        user.setLastModify(new Date());
        userinfoDAO.insertUser(user);
        String ticket = addToken(user.getId());
        map.put("ticket", ticket);
        return map;
    }


    public Userinfo updateUserinfo(int userId,String nickname,String email){
        try {
            Userinfo userinfo = new Userinfo();
            userinfo.setId(userId);
            userinfo.setNickname(nickname);
            userinfo.setEmail(email);
            int i = userinfoDAO.updateUserinfoSelective(userinfo);
            if (i > 0){
                return userinfoDAO.selectById(userId);
            }
        }catch (Exception e){
            logger.error("更新用户异常" + e.getMessage());
        }
        return null;
    }

    public int updatePassword(int userId,String password){
        String salt = UUID.randomUUID().toString().substring(0, 5);
        return userinfoDAO.updatePasswordById(userId,HifriendsUtil.MD5(password+salt),salt,new Date());
    }

    private String addToken(int userId){
        Token token = new Token();
        Date now = new Date();
        now.setTime(now.getTime() + 7*24*60*60*1000);
        token.setExpired(now);
        token.setUserId(userId);
        token.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        token.setStatus(0);
        tokenDAO.insertToken(token);
        return token.getTicket();
    }

    public int updatePhoto(int userId,String headUrl){
        Userinfo userinfo = new Userinfo();
        userinfo.setId(userId);
        userinfo.setHeadUrl(headUrl);
        return userinfoDAO.updateUserinfoSelective(userinfo);
    }

}

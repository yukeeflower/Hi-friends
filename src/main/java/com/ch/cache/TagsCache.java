package com.ch.cache;

import com.ch.dao.UserinfoDAO;
import com.ch.model.Userinfo;
import com.ch.utils.JedisApapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2018/3/25.
 */
@Component
public class TagsCache implements InitializingBean {
    private static final Logger logger = LoggerFactory.getLogger(JedisApapter.class);

    private static final String keyPrefix = "tags_user";

    private JedisPool jedisPool;
    @Autowired
    private  UserinfoDAO userinfoDAO;

    @Override
    public void afterPropertiesSet() throws Exception {
        jedisPool = new JedisPool("redis://localhost:6379/1");
    }



    public  List<String> lrange(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (jedis.exists(keyPrefix+key)){
                return jedis.lrange(String.valueOf(keyPrefix + key),-100,100);
            }else {
                List<String>list = new ArrayList<>();
                Userinfo userinfo = userinfoDAO.selectById(Integer.parseInt(key));
                if (userinfo != null){
                    String[] str = userinfo.getTags().split(";");
                    for (String s:str){
                        list.add(s);
                    }
                }
                return list;
            }
        }catch (Exception e){
            logger.error("TagsCache get exception" + e.getMessage());
        }
        finally {
            if (jedis !=null){
                jedis.close();

            }
        }
        return null;
    }

    public  void lpush(String key,Object value){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(keyPrefix+key,value.toString());
        }catch (Exception e){
            e.printStackTrace();
            logger.error("tags lpush error" + e.getMessage());
        }
        finally {
            if (jedis !=null){
                jedis.close();

            }
        }
    }
}

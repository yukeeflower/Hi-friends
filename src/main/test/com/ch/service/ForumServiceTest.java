package com.ch.service;

import com.ch.cache.TagsCache;
import com.ch.model.Forum;
import com.ch.model.ViewObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by apple on 2018/3/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:beans.xml"})
public class ForumServiceTest {

    @Autowired
    private ForumService forumService;
    @Autowired
    private UserinfoService userinfoService;
    @Autowired
    TagsCache tagsCache;
    @Test
    public void getLatestForums() throws Exception {
    }

    @Test
    public void addForum() throws Exception {
        forumService.addForum(1,"今天天气真晴朗","萨顶顶撒大所大所多萨达刚发的广泛地都是废话金卡戴珊发送客户打算打了肯定会萨克良好的萨带回来喀什烦得很即可更好地萨科技发货湿哒哒所","产品运营","sadsa");
    }

    @Test
    public void updateForumPics() throws Exception {
        forumService.updateForumPics(1,"http//asdasdsa");
    }

    @Test
    public void test(){
        tagsCache.lpush(String.valueOf(1),String.valueOf(1));
        tagsCache.lpush(String.valueOf(1),String.valueOf(2));
        tagsCache.lpush(String.valueOf(1),String.valueOf(3));
        tagsCache.lpush(String.valueOf(1),String.valueOf(4));
        tagsCache.lpush(String.valueOf(1),String.valueOf(5));
        System.out.println(tagsCache.lrange(String.valueOf(1)));
    }
}
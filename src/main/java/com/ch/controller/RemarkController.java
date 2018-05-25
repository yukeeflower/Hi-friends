package com.ch.controller;

import com.ch.model.HostHolder;
import com.ch.model.Remark;
import com.ch.service.ForumService;
import com.ch.service.RemarkService;
import com.ch.utils.HifriendsUtil;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by apple on 2018/3/7.
 */
@Controller
public class RemarkController {

    private static final Logger logger = LoggerFactory.getLogger(RemarkController.class);

    @Autowired
    private RemarkService remarkService;
    @Autowired
    private ForumService forumService;
    @Autowired
    private HostHolder hostHolder;

    @RequestMapping(value = "addForumRemark",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String addForumRemark(@RequestBody Map<String,Object> map, HttpServletRequest request, HttpServletResponse response){
        if (hostHolder.getUsers()==null){
            return HifriendsUtil.getJSONString(300,"您还未登录，请登录后在发言");
        }else {
            try {
                String content = "";
                int forumId = 0;
                if (map.containsKey("content")){
                    content = map.get("content").toString();
                }
                if (map.containsKey("forumId")){
                    forumId = Integer.parseInt(map.get("forumId").toString());
                }
                if (!content.equals("")&&forumId!=0){
                    remarkService.addRemark(content, Remark.REMARK_TYPE_FORUM,forumId,hostHolder.getUsers().getId());
                    forumService.addRemarkNum(forumId);
                    return HifriendsUtil.getJSONString(200,"增加评论成功");
                }else {
                    return HifriendsUtil.getJSONString(500,"增加评论失败");
                }

            }catch (Exception e){
                logger.error("增加评论失败"+e.getMessage());
                e.printStackTrace();
                return HifriendsUtil.getJSONString(400,"增加评论失败");
            }
        }
    }
}

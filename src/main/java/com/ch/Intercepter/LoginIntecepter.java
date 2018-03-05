package com.ch.Intercepter;

import com.ch.dao.TokenDAO;
import com.ch.dao.UserinfoDAO;
import com.ch.model.HostHolder;
import com.ch.model.Token;
import com.ch.model.Userinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by apple on 2018/3/5.
 */
@Component
public class LoginIntecepter implements HandlerInterceptor{
    @Autowired
    private UserinfoDAO userinfoDAO;
    @Autowired
    private HostHolder hostHolder;
    @Autowired
    private TokenDAO tokenDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String ticket = null;
        Cookie[] cookies = request.getCookies();
        if (cookies!=null){
            for (Cookie cookie : cookies){
                if (cookie.getName().equals("ticket")){
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket!=null){
            Token ticket1 = tokenDAO.selectByToken(ticket);
            if (ticket1 == null || ticket1.getExpired().before(new Date())||ticket1.getStatus()==1){
                return true;
            }
            Userinfo userinfo = userinfoDAO.selectById(ticket1.getId());
            hostHolder.setUsers(userinfo);
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null){
            modelAndView.addObject("user",hostHolder.getUsers());
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        hostHolder.clearUsers();
    }
}

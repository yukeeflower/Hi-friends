package com.ch.configuration;

import com.ch.Intercepter.LoginIntecepter;
import com.ch.Intercepter.LoginRequiredInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by apple on 2018/3/5.
 */
@Component
public class HifriendsWebConfiguration extends WebMvcConfigurerAdapter{

    @Autowired
    private LoginRequiredInterceptor loginRequiredInterceptor;
    @Autowired
    private LoginIntecepter loginIntecepter;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntecepter).addPathPatterns("/**");
        registry.addInterceptor(loginRequiredInterceptor).addPathPatterns("/user/**").excludePathPatterns("/user/login");
        super.addInterceptors(registry);
    }
}

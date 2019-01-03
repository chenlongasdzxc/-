package com.slicepoker.zps.project.Config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Zps
 * @date 2018/12/26 11:35
 **/
@Configuration
public class Config {

    @Bean
    public FilterRegistrationBean TokenFilter(){
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new TokenFilter());
        registrationBean.addUrlPatterns("/Score/*");
        registrationBean.addUrlPatterns("/Sketch/*");
        return registrationBean;
    }

}

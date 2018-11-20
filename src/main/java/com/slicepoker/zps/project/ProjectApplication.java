package com.slicepoker.zps.project;


import com.slicepoker.zps.project.Pojo.User;
import org.apache.tomcat.util.descriptor.web.LoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@SpringBootApplication
public class ProjectApplication implements WebMvcConfigurer{

    public static void main(String[] args) {
        SpringApplication.run(ProjectApplication.class, args);
    }

    public final static String SESSION_KEY = "user";
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .maxAge(3600);
    }

    class SecurityInterceptor extends HandlerInterceptorAdapter{
        @Override
        public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
            User user = (User) request.getSession().getAttribute("user");
            if (user==null){
                response.sendRedirect("/login.html");
                return false;
            }
            return true;
        }
    }

}

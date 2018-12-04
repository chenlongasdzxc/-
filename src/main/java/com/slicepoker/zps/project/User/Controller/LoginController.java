package com.slicepoker.zps.project.User.Controller;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zps
 * @date 2018/10/12 16:54
 **/
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;


   @GetMapping("/login")
    public Commes Login(String userName, String userPassword, HttpServletRequest request){
        return loginService.Login(userName, userPassword,request);
    }



}

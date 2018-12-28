package com.slicepoker.zps.project.User.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Zps
 * @date 2018/10/12 16:54
 **/
public interface LoginService {

    Commes Login(String userName, String userPassword, HttpServletRequest request);


}

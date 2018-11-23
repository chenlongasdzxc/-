package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.User;
import com.slicepoker.zps.project.User.Respority.UserRespority;
import com.slicepoker.zps.project.User.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * @author Zps
 * @date 2018/10/12 16:54
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRespority userRespority;

    @Override
    public Commes Login(String userName, String userPassword, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            User user = userRespority.findByUserNameAndDeletedIsFalse(userName);
            if (user!=null){
                Objects.equals(user.getUserPassword(),userPassword);
                session.setAttribute("user",user);
                System.out.println(session.getId());
                return Commes.successMes();
            }else {
                return Commes.errorMes("500","学号不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","密码错误");
        }
    }
}

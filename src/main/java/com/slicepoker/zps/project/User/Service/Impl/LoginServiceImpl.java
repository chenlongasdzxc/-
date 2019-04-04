package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import com.slicepoker.zps.project.Permission.Respority.RoleRespority;
import com.slicepoker.zps.project.Permission.Respority.UserRoleContactRespority;
import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.User;
import com.slicepoker.zps.project.User.Respority.UserRespority;
import com.slicepoker.zps.project.User.Service.LoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;

/**
 * @author Zps
 * @date 2018/10/12 16:54
 **/
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserRespority userRespority;

    @Autowired
    private UserRoleContactRespority userRoleContactRespority;

    @Override
    public Commes Login(String userName, String userPassword, HttpServletRequest request) {
        try {
            HttpSession session = request.getSession();
            User user = userRespority.findByUserNameAndDeletedIsFalse(userName);
            if (user!=null){
                if(Objects.equals(user.getUserPassword(),userPassword)) {
                    session.setAttribute("studentNumber", user.getStudentNumber());
                    session.setMaxInactiveInterval(30 * 60);
                    System.out.println(session.getId());
                    return Commes.success(setToken(user));
                }else{
                    return Commes.errorMes("401","密码错误");
                }
            }else {
                return Commes.errorMes("405","学号不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","error");
        }
    }


    public Commes setToken(User user){
        UserRoleContact userRoleContact = userRoleContactRespority.findByStudentNumberAndDeletedIsFalse(user.getId());
        String role = userRoleContact.getRoleName();
        String token = Jwts.builder().setSubject(user.getUserName()).claim("roles",role).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretkey").compact();
        return Commes.success(token);

    }


}

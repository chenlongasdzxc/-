package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import com.slicepoker.zps.project.Permission.Respority.RoleRespority;
import com.slicepoker.zps.project.Permission.Respority.UserRoleContactRespority;
import com.slicepoker.zps.project.Permission.Service.RolePressionContactService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.ReturnData;
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
                    ReturnData returnData = new ReturnData();
                    returnData.setMessage(setToken(user));
                    returnData.setNumber(findStudentNumber(user.getUserName()));
                    return Commes.success(returnData);
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


    private Long findStudentNumber(String userName){
        Long studentNumber = userRespority.findNumber(userName,false);
        return studentNumber;
    }



    private String setToken(User user){
        UserRoleContact userRoleContact = userRoleContactRespority.findByStudentNumberAndDeletedIsFalse(user.getId());
        String role;
        if(userRoleContact!=null){
            role = userRoleContact.getRoleName();
        }else {
            role = null;
        }
        String token = Jwts.builder().setSubject(user.getUserName()).claim("roles",role).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256,"secretkey").compact();
        return token;

    }


}

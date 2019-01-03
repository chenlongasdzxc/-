package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.User;
import com.slicepoker.zps.project.User.Respority.StudentInfoRespority;
import com.slicepoker.zps.project.User.Respority.UserRespority;
import com.slicepoker.zps.project.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2018/10/12 17:42
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespority userRespority;

    @Autowired
    private StudentInfoRespority studentInfoRespority;

    /**
     * @param id 学号
     * @param userPassword  密码
     * 通过学号修改密码
     * **/
    @Override
    public Commes changePassword(Long id, String userPassword) {
        try {
            User user = userRespority.findByIdAndDeletedIsFalse(id);
            if (user!=null){
                user.setUserPassword(userPassword);
                return Commes.success(userRespority.save(user));
            }else {
                return Commes.errorMes("401","学号错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","修改密码失败");
        }
    }


    /**
     * @param studentNumber 学号
     * @param gradeLevels
     * **/
    @Override
    public Commes setGradeLevels(Long gradeLevels, Long studentNumber) {
        try{
            User user = userRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (user!=null) {
                user.setGradeLeave(gradeLevels);
                return Commes.success(userRespority.save(user));
            }else {
                return Commes.errorMes("401","不存在该人员");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","设置权限失败");
        }
    }

    /**
     * @param user
     * **/
    @Override
    public Commes undateUser(User user) {
        try {
            User user1 = userRespority.findByUserNameAndDeletedIsFalse(user.getUserName());
            if (user1==null){
                return Commes.success(userRespority.save(user));
            }else {
                return Commes.errorMes("401","账号已存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","添加失败");
        }
    }

    /**
     * @param studentNumber
     * **/
    public void setUser(Long studentNumber){
        if (studentNumber!=null){
            User user = userRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (user==null){
                user = new User();
                user.setStudentNumber(studentNumber);
                user.setUserName(Long.toString(studentNumber));
                user.setUserPassword(Long.toString(studentNumber));
            }
            userRespority.save(user);
        }
    }

    /**
     * @param userName
     * 查找id
     * **/
    @Override
    public Commes findId(String userName) {
        try {
            Long id = userRespority.findId(userName);
            if (id!=null){
                return Commes.success(id);
            }else {
                return Commes.errorMes("401","查找失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","error");
        }
    }
}

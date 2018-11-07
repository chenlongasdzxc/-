package com.slicepoker.zps.project.Service.Impl;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Pojo.User;
import com.slicepoker.zps.project.Respority.UserRespority;
import com.slicepoker.zps.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zps
 * @date 2018/10/12 17:42
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespority userRespority;

    @Override
    public Commes changePassword(Long studentNumber, String userPassword) {
        try {
            User user = userRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (user!=null){
                user.setUserPassword(userPassword);
                return Commes.success(userRespority.save(user));
            }else {
                return Commes.errorMes("500","学号错误");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","修改密码失败");
        }
    }


    //设置权限
    @Override
    public Commes setGradeLevels(Long gradeLevels, Long studentNumber) {
        try{
            User user = userRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (user!=null) {
                user.setGradeLeave(gradeLevels);
                return Commes.success(userRespority.save(user));
            }else {
                return Commes.errorMes("500","不存在该人员");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","设置权限失败");
        }
    }


}

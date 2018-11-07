package com.slicepoker.zps.project.Controller;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2018/10/12 17:41
 **/
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //修改密码
    @PostMapping("/changePassword")
    public Commes changePassword(Long studentNumber,String userPassword){
        return userService.changePassword(studentNumber, userPassword);
    }

    //权限人员设置用户权限
    @PostMapping("/setLevels")
    public Commes setLevels(Long studentNumber,Long gradeLevels){
        return userService.setGradeLevels(studentNumber, gradeLevels);
    }
}

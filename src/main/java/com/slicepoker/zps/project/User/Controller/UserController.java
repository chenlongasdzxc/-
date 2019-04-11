package com.slicepoker.zps.project.User.Controller;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.User;
import com.slicepoker.zps.project.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2018/10/12 17:41
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //修改密码
    @PostMapping("/changePassword")
    public Commes changePassword(@RequestBody User user){
        return userService.changePassword(user.getId(), user.getUserPassword());
    }

    @GetMapping("/find")
    public Commes findId(String userName){
        return userService.findId(userName);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(User user, Pageable pageable){
        return userService.findFuzzy(user, pageable);
    }

    @PostMapping("/add")
    public Commes addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @GetMapping("/delete")
    public Commes deleteUser(Long id){ return userService.deleteUser(id); }
}

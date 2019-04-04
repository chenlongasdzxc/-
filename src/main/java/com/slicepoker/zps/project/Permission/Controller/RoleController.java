package com.slicepoker.zps.project.Permission.Controller;

import com.slicepoker.zps.project.Permission.Pojo.Role;
import com.slicepoker.zps.project.Permission.Service.RoleService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/4/4 15:04
 **/
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/add")
    public Commes add(@RequestBody Role role){
        return roleService.add(role);
    }

    @GetMapping("/update")
    public Commes update(Role role){
        return roleService.update(role);
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return roleService.delete(id);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(Role role, Pageable pageable){
        return roleService.findFuzzy(role, pageable);
    }
}

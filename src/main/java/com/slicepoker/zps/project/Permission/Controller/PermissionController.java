package com.slicepoker.zps.project.Permission.Controller;

import com.slicepoker.zps.project.Permission.Pojo.Permission;
import com.slicepoker.zps.project.Permission.Service.PermissionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/4/4 15:07
 **/
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping("/add")
    public Commes add(@RequestBody Permission permission){
        return permissionService.add(permission);
    }

    @GetMapping("/update")
    public Commes update(Permission permission){
        return permissionService.update(permission);
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return permissionService.delete(id);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(Permission permission, Pageable pageable){
        return permissionService.findFuzzy(permission, pageable);
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return permissionService.findAll();
    }

}

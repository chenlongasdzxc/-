package com.slicepoker.zps.project.Permission.Controller;

import com.slicepoker.zps.project.Permission.Pojo.RolePermissionContact;
import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import com.slicepoker.zps.project.Permission.Respority.RolePermissionRespority;
import com.slicepoker.zps.project.Permission.Service.RolePressionContactService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2019/4/4 15:13
 **/
@RestController
@RequestMapping("/rolePermission")
public class RolePermissionContactController {

    @Autowired
    private RolePressionContactService rolePressionContactService;

    @Autowired
    private RolePermissionRespority rolePermissionRespority;

    @GetMapping("/findByRoleCode/{roleCode}")
    public Commes findByRoleCode(@PathVariable(name="roleCode") String roleCode){
        return rolePressionContactService.findByRoleCode(roleCode);
    }


    @PostMapping("/add/{roleCode}/{roleName}")
    public Commes add(@PathVariable(name="roleCode") String roleCode,
                      @PathVariable(name="roleName") String roleName,
                      @RequestBody List<RolePermissionContact> params){
        Boolean success = true;
        Commes commes;
        rolePressionContactService.deleteRolePermission(roleCode);
        if (params.size()>0){
            for (RolePermissionContact rolePermissionContact:params){
                if (success){
                    commes = rolePressionContactService.add(roleName,roleCode,rolePermissionContact.getPermissionCode());
                    if (!Objects.equals(commes.getCode(),"200"))
                        success = false;
                }else {
                    break;
                }
            }
        }else {
            success = true;
        }
        return success ? Commes.successMes() : Commes.errorMes("401","保存失败");
    }


    @GetMapping("/findPermissionCode/{studentNumber}")
    public Commes findPermissionCode(@PathVariable(name="studentNumber") Long studentNumber){
        return rolePressionContactService.findPermissionCode(studentNumber);
    }



}

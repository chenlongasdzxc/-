package com.slicepoker.zps.project.Permission.Service.Impl;

import com.slicepoker.zps.project.Permission.Pojo.Permission;
import com.slicepoker.zps.project.Permission.Pojo.RolePermissionContact;
import com.slicepoker.zps.project.Permission.Respority.PermissionRespority;
import com.slicepoker.zps.project.Permission.Respority.RolePermissionRespority;
import com.slicepoker.zps.project.Permission.Respority.RoleRespority;
import com.slicepoker.zps.project.Permission.Service.RolePressionContactService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/4 15:11
 **/
@Service
public class RolePermissionContactServiceImpl implements RolePressionContactService {

    @Autowired
    private RolePermissionRespority rolePermissionRespority;

    @Autowired
    private PermissionRespority permissionRespority;


    /**
     * @param roleCode
     * **/
    @Override
    public Commes findByRoleCode(String roleCode) {
        try {
            List<RolePermissionContact> list = rolePermissionRespority.findByRoleCodeAndDeletedIsFalse(roleCode);
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    /**
     * @param roleCode
     * @param permissionCode
     * @param roleName
     * @description 新增
     * **/
    @Override
    public Commes add(String roleName, String roleCode, String permissionCode) {
        try {
                RolePermissionContact rolePermissionContact = new RolePermissionContact();
                rolePermissionContact.setPermissionCode(permissionCode);
                rolePermissionContact.setPermissionName(findPermissionName(permissionCode));
                rolePermissionContact.setRoleCode(roleCode);
                rolePermissionContact.setRoleName(roleName);
                rolePermissionRespority.save(rolePermissionContact);
                return Commes.successMes();
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

   @Override
    public void deleteRolePermission(String roleCode){
        List<RolePermissionContact> list = rolePermissionRespority.findByRoleCodeAndDeletedIsFalse(roleCode);
        if (list.size()>0) {
            for (RolePermissionContact rolePermissionContact : list) {
                rolePermissionContact.setDeleted(true);
                rolePermissionRespority.save(rolePermissionContact);
            }
        }
    }


    private String findPermissionName(String permissionCode){
        Permission permission = permissionRespority.findByPermissionCodeAndDeletedIsFalse(permissionCode);
        if (permission!=null){
            return permission.getPermissionName();
        }else {
            return null;
        }
    }
}



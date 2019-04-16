package com.slicepoker.zps.project.Permission.Service.Impl;

import com.slicepoker.zps.project.Permission.Pojo.Permission;
import com.slicepoker.zps.project.Permission.Pojo.RolePermissionContact;
import com.slicepoker.zps.project.Permission.Respority.PermissionRespority;
import com.slicepoker.zps.project.Permission.Respority.RolePermissionRespority;
import com.slicepoker.zps.project.Permission.Respority.RoleRespority;
import com.slicepoker.zps.project.Permission.Respority.UserRoleContactRespority;
import com.slicepoker.zps.project.Permission.Service.RolePressionContactService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private UserRoleContactRespority userRoleContactRespority;


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

    /**
     * @param studentNumber
     * @description 查询权限代码
     * **/
    @Override
    public Commes findPermissionCode(Long studentNumber) {
        try {
            List<String> list = findRoleCode(studentNumber);
            if (list!=null){
                List list1 = new ArrayList();
                for (String roleCode:list) {
                    List<String> permissionCode = rolePermissionRespority.findPermissionCode(roleCode);
                    list1.addAll(permissionCode);
                }
                if (list1.size()>0){
                    return Commes.success(list1);
                }else {
                    return Commes.success(list1);
                }
            }else {
                return Commes.errorMes("401","没有权限");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
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


    private List<String> findRoleCode(Long StudentNumber){
        try {
            List<String> list = userRoleContactRespority.findRoleCode(StudentNumber);
            if (list.size()>0){
                return list;
            }else {
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }



}



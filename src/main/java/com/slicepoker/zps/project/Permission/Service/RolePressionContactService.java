package com.slicepoker.zps.project.Permission.Service;

import com.slicepoker.zps.project.Permission.Pojo.RolePermissionContact;
import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2019/4/4 15:10
 **/
public interface RolePressionContactService {


    Commes add(String roleName,String roleCode,String permissionCode);

    Commes findByRoleCode(String roleCode);

    void deleteRolePermission(String roleCode);

}

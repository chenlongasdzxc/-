package com.slicepoker.zps.project.Permission.Respority;

import com.slicepoker.zps.project.Permission.Pojo.RolePermissionContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/4 15:09
 **/
public interface RolePermissionRespority extends JpaRepository<RolePermissionContact,Long>, JpaSpecificationExecutor<RolePermissionContact> {



    List findByRoleCodeAndDeletedIsFalse(String roleCode);


    @Query("select permissionCode from RolePermissionContact where roleCode=?1 and deleted = false ")
    List<String> findPermissionCode(String roleCode);

}

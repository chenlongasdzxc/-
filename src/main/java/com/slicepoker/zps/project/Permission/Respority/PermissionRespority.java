package com.slicepoker.zps.project.Permission.Respority;

import com.slicepoker.zps.project.Permission.Pojo.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/4/4 15:05
 **/
public interface PermissionRespority extends JpaRepository<Permission,Long>, JpaSpecificationExecutor<Permission> {

    Permission findByIdAndDeletedIsFalse(Long id);

    Permission findByPermissionNameAndDeletedIsFalse(String permissionName);
}

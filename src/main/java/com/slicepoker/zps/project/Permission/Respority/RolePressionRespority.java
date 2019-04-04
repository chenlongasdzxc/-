package com.slicepoker.zps.project.Permission.Respority;

import com.slicepoker.zps.project.Permission.Pojo.RolePermissionContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/4/4 15:09
 **/
public interface RolePressionRespority extends JpaRepository<RolePermissionContact,Long>, JpaSpecificationExecutor<RolePermissionContact> {

}

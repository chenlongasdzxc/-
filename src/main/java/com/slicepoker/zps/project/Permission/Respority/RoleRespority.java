package com.slicepoker.zps.project.Permission.Respority;

import com.slicepoker.zps.project.Permission.Pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author Zps
 * @date 2019/4/4 15:04
 **/
public interface RoleRespority extends JpaRepository<Role,Long>, JpaSpecificationExecutor<Role> {

    Role findByIdAndDeletedIsFalse(Long id);

    Role findByRoleNameAndDeletedIsFalse(String roleName);

    List findAllByDeletedIsFalse();

    Role findByRoleCodeAndDeletedIsFalse(String roleName);
}

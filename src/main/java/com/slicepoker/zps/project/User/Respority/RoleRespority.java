package com.slicepoker.zps.project.User.Respority;

import com.slicepoker.zps.project.User.Pojo.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Zps
 * @date 2018/12/26 10:43
 **/
public interface RoleRespority extends JpaRepository<Role,Long> {

    @Query(value="select roleName from Role where userId =?1")
    String findRoleName(Long userId);


}

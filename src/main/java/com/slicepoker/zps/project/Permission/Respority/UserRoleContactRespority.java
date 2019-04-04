package com.slicepoker.zps.project.Permission.Respority;

import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Zps
 * @date 2019/4/4 15:13
 **/
public interface UserRoleContactRespority extends JpaRepository<UserRoleContact,Long>, JpaSpecificationExecutor<UserRoleContact> {


    UserRoleContact findByStudentNumberAndDeletedIsFalse(Long studentNumber);
}

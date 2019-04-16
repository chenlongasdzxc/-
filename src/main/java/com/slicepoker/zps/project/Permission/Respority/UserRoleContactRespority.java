package com.slicepoker.zps.project.Permission.Respority;

import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/4 15:13
 **/
public interface UserRoleContactRespority extends JpaRepository<UserRoleContact,Long>, JpaSpecificationExecutor<UserRoleContact> {


    UserRoleContact findByStudentNumberAndDeletedIsFalse(Long studentNumber);

    List findByStudentNumberAndUserNameAndDeletedIsFalse(Long studentNumber,String userName);

    UserRoleContact findByIdAndDeletedIsFalse(Long id);

    UserRoleContact findByRoleNameAndStudentNumberAndDeletedIsFalse(String roleName,Long studentNumber);

    UserRoleContact findByRoleCodeAndStudentNumberAndDeletedIsFalse(String roleCode,Long studentNumber);

    @Query(value="select roleCode from UserRoleContact where studentNumber =?1 and deleted = false")
    List<String> findRoleCode(Long StudentNumber);

}

package com.slicepoker.zps.project.User.Respority;

import com.slicepoker.zps.project.User.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Zps
 * @date 2018/10/12 16:59
 **/
public interface UserRespority extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {


    User findByStudentNumberAndDeletedIsFalse(Long studentNumber);

    User findByUserNameAndDeletedIsFalse(String userName);

    User findByGradeLeaveAndAndDeletedIsFalse(Long gradeLeave);

    User findByUserNameAndDeletedIsFalse(Long userName);

    User findByIdAndDeletedIsFalse(Long id);

    @Query("select studentNumber from User where userName =?1 and deleted =?2")
    Long findNumber(String userName,boolean isdelete);


    @Query("select id from User where userName =?1")
    Long findId(String userName);
}

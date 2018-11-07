package com.slicepoker.zps.project.Respority;

import com.slicepoker.zps.project.Pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2018/10/12 16:59
 **/
public interface UserRespority extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {


    User findByStudentNumberAndDeletedIsFalse(Long studentNumber);

    User findByUserNameAndDeletedIsFalse(String userName);

    User findByGradeLeaveAndAndDeletedIsFalse(Long gradeLeave);
}

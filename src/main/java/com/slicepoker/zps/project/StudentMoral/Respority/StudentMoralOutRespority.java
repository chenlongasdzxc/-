package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/3/14 8:51
 **/
public interface StudentMoralOutRespority extends JpaRepository<StudentMoralOut,Long>, JpaSpecificationExecutor<StudentMoralOut> {

    StudentMoralOut findByIdAndDeletedIsFalse(Long id);
}

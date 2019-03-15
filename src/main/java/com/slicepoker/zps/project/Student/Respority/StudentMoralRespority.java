package com.slicepoker.zps.project.Student.Respority;

import com.slicepoker.zps.project.Student.Pojo.StudentMoral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/3/11 16:07
 **/
public interface StudentMoralRespority extends JpaRepository<StudentMoral,Long>, JpaSpecificationExecutor<StudentMoral> {

    StudentMoral findByStudentNumberAndDeletedIsFalse(Long studentNumber);
}

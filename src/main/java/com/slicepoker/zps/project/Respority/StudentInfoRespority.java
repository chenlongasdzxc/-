package com.slicepoker.zps.project.Respority;

import com.slicepoker.zps.project.Pojo.StudentInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2018/10/18 15:25
 **/
public interface StudentInfoRespority extends JpaRepository<StudentInformation,Long>, JpaSpecificationExecutor<StudentInformation> {

        StudentInformation findByStudentClassAndGrade(String studentClass,Long grade);
}

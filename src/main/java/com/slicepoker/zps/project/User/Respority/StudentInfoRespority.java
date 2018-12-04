package com.slicepoker.zps.project.User.Respority;

import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/10/18 15:25
 **/
public interface StudentInfoRespority extends JpaRepository<StudentInformation,Long>, JpaSpecificationExecutor<StudentInformation> {

    StudentInformation findByStudentNumber(Long studentNumber);

    List<StudentInformation> findByStudentClass(String studentClass);

}

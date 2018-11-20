package com.slicepoker.zps.project.Respority;

import com.slicepoker.zps.project.Pojo.StudentInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/10/18 15:25
 **/
public interface StudentInfoRespority extends JpaRepository<StudentInformation,Long>, JpaSpecificationExecutor<StudentInformation> {

     /**
      * 更具班级以及年级查询学生
      * @param studentClass
      * @param grade
      * **/
     StudentInformation findByStudentClassAndGrade(String studentClass,Long grade);

     StudentInformation findByStudentCode(Long studentCode);

     /*@Query(value="select roomNumber from TB_StudentInformation")
     List<StudentInformation> findroomNumber();*/
}

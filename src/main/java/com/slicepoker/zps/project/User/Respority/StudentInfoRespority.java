package com.slicepoker.zps.project.User.Respority;

import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

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

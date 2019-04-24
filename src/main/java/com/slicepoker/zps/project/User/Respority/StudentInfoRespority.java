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

    StudentInformation findByStudentNumberAndDeletedIsFalse(Long studentNumber);

    /**通过专业查询学号List**/
    @Query(value="select studentNumber from StudentInformation where major =?1")
    List findStudentNumberListByMajor(String major);

    @Query(value="select studentNumber from StudentInformation ")
    List findStudentNumberList();

    List<StudentInformation> findByFileCard(Boolean fileCard);

    List<StudentInformation> findByGradeAndStudentClassAndDeletedIsFalse(Long grade,String studentClass);

    @Query(value="select distinct(major) from StudentInformation where deleted = false ")
    List findMajorList();

    @Query(value="select distinct(studentClass) from StudentInformation where major =?1 and deleted = false ")
    List findStudentClass(String major);


}

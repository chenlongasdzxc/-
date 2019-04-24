package com.slicepoker.zps.project.Grade.Respority;

import com.slicepoker.zps.project.Grade.Pojo.StudentGPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/23 14:35
 **/
public interface StudentGPARespority extends JpaRepository<StudentGPA,Long>, JpaSpecificationExecutor<StudentGPA> {

    StudentGPA findByIdAndDeletedIsFalse(Long id);

    StudentGPA findByYearAndMajorAndDeletedIsFalse(String year,String major);

    List findByDeletedIsFalse();

    List findByMajorAndYearAndDeletedIsFalse(String major,String year);

    @Query(value="SELECT group_concat(gpa_name)  FROM tb_student_gpa where year =?1 and major =?2 and deleted = false",nativeQuery=true)
    String selectGpaName(String year,String major);

    StudentGPA findByYearAndMajorAndGpaNameAndDeletedIsFalse(String year,String major,String gpaName);

    @Query(value="select sum(credit) from StudentGPA where major =?1 and year =?2 and deleted = false ")
    double creditSum(String major,String year);
}

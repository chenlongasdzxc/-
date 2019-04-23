package com.slicepoker.zps.project.Grade.Respority;

import com.slicepoker.zps.project.Grade.Pojo.StudentGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/22 16:14
 **/
public interface StudentGradeRespority extends JpaRepository<StudentGrade,Long>, JpaSpecificationExecutor<StudentGrade> {

    StudentGrade findByStudentNumberAndGradeNameAndGradeYear(Long studentNumber,String gradeName,String gradeYear);

    @Query(value="select distinct(gradeName) from StudentGrade where gradeYear =?1 and deleted = false and major =?2")
    List findMajorGradeNameList(String gradeYear,String major);

    @Query(value="select distinct(gradeName) from StudentGrade where gradeYear =?1 and deleted = false")
    List findGradeNameList(String gradeYear);

    StudentGrade findByIdAndDeletedIsFalse(Long id);
}

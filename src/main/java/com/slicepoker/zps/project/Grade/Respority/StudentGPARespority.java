package com.slicepoker.zps.project.Grade.Respority;

import com.slicepoker.zps.project.Grade.Pojo.StudentGPA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/23 14:35
 **/
public interface StudentGPARespority extends JpaRepository<StudentGPA,Long>, JpaSpecificationExecutor<StudentGPA> {

    StudentGPA findByIdAndDeletedIsFalse(Long id);

    StudentGPA findByYearAndMajorAndDeletedIsFalse(String year,String major);

    List findByDeletedIsFalse();
}

package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOutTotal;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlusTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/4/18 14:29
 **/
public interface StudentMoralPlusTotalRespority extends JpaRepository<StudentMoralPlusTotal,Long>, JpaSpecificationExecutor<StudentMoralPlusTotal> {

    StudentMoralPlusTotal findByStudentNumberAndMoralPlusYearAndDeletedIsFalse(Long studentNumber, String moralOutYear);
}

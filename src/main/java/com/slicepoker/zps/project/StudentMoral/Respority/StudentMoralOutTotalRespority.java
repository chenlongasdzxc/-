package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeductionTotal;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOutTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/4/17 16:01
 **/
public interface StudentMoralOutTotalRespority extends JpaRepository<StudentMoralOutTotal,Long>, JpaSpecificationExecutor<StudentMoralOutTotal> {

    StudentMoralOutTotal findByStudentNumberAndMoralOutYearAndDeletedIsFalse(Long studentNumber,String moralOutYear);
}

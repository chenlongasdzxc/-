package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeductionTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/4/17 11:34
 **/
public interface StudentMoralDeductionTotalRespority extends JpaRepository<StudentMoralDeductionTotal,Long>, JpaSpecificationExecutor<StudentMoralDeductionTotal> {

    StudentMoralDeductionTotal findByStudentNumberAndYearAndDeletedIsFalse(Long studentNumber,String year);
}

package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpressionTotal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/4/1 15:26
 **/
public interface StudentMoralExpressionTotalRespority extends JpaRepository<StudentMoralExpressionTotal,Long>, JpaSpecificationExecutor<StudentMoralExpressionTotal> {

    StudentMoralExpressionTotal findByStudentNumberAndMoralExpressionYearAndDeletedIsFalse(Long studentNumber,String moralExpressionYear);
}

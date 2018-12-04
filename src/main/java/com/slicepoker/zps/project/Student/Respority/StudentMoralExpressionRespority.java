package com.slicepoker.zps.project.Student.Respority;

import com.slicepoker.zps.project.Student.Pojo.StudentMoralExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2018/12/4 16:06
 **/
public interface StudentMoralExpressionRespority extends JpaRepository<StudentMoralExpression,Long>, JpaSpecificationExecutor<StudentMoralExpression> {
}

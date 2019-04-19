package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2019/3/22 16:48
 **/
public interface StudentMoralExpressionRespority extends JpaRepository<StudentMoralExpression,Long>, JpaSpecificationExecutor<StudentMoralExpression> {


    List findByStudentNumberAndDeletedIsFalse(Long studentNumber);

    StudentMoralExpression findByStudentNumberAndMoralExpressionNameAndMoralExpressionYearAndDeletedIsFalse(Long studentNumber,String moralExpressionName,String moralExpressionYear);

    StudentMoralExpression findByIdAndDeletedIsFalse(Long id);

    @Query(value="select sum(moralExpressionScore) from StudentMoralExpression where studentNumber =?1 and moralExpressionYear=?2 and states = 'ME002'")
    double moralExpressionScore(Long studentNumber,String moralExpressionYear);

    @Query(value="SELECT group_concat(moral_expression_name) FROM tb_student_moral_expression where student_number =?1 and moral_expression_year =?2 and states = 'ME002' and deleted = false ",nativeQuery=true)
    String moralExpressionNameList(Long studentNumber,String moralExpressionYear);
}

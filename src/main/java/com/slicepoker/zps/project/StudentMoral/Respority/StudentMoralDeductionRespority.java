package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/15 14:38
 **/
public interface StudentMoralDeductionRespority extends JpaRepository<StudentMoralDeduction,Long>, JpaSpecificationExecutor<StudentMoralDeduction> {

    StudentMoralDeduction findByIdAndDeletedIsFalse(Long id);

    List findByStudentNumberAndMoralDeductionYearAndDeletedIsFalse(Long studentNumber,String moralDeductionYear);

    @Query(value="select sum(moralDeductionScore) from StudentMoralDeduction where studentNumber =?1 and states = 'MD002' and moralDeductionYear =?2 and deleted = false ")
    Double sumMoralDeductionScore(Long studentNumber,String moralDeductionYear);

    @Query(value="SELECT gropu_concat(moral_deduction_type) from tb_student_moral_deduction where student_number =?1 and states = 'MD002' and moral_deduction_year =?2 and deleted = false",nativeQuery=true)
    String moralDeductionName(Long studentNumber,String moralDeductionYear);
}

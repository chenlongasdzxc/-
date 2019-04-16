package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/15 14:38
 **/
public interface StudentMoralDeductionRespority extends JpaRepository<StudentMoralDeduction,Long>, JpaSpecificationExecutor<StudentMoralDeduction> {

    StudentMoralDeduction findByIdAndDeletedIsFalse(Long id);

    List findByStudentNumberAndMoralDeductionYearAndDeletedIsFalse(Long studentNumber,String moralDeductionYear);
}

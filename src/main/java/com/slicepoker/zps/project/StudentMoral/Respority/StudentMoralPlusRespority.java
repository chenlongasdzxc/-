package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2019/3/6 16:20
 **/
public interface StudentMoralPlusRespority extends JpaRepository<StudentMoralPlus,Long>, JpaSpecificationExecutor<StudentMoralPlus> {


    StudentMoralPlus findByIdAndDeletedIsFalse(Long id);

    StudentMoralPlus findByIdAndStatesAndDeletedIsFalse(Long id,String states);

    @Query(value="select sum(moralPlusScore) from StudentMoralPlus where studentNumber=?1 and year=?2 and states=?3")
    double sumPersonalMoralPlusScore(String year,String states);

    List findByStudentNumberAndComprehensiveQualityStatesAndDeletedIsFalse(Long studentNumber,String comprehensiveQualityStates);

    @Query(value="select moralPlusScore from StudentMoralPlus where studentNumber=?1 and moralPlusType=?2 and moralPlusName =?3 and year =?4 and comprehensiveQualityStates =?5 and deleted = false ")
    List findStudentMoralPlus(Long studentNumber,
                              String moralPlusType,
                              String moralPlusName,
                              String year,
                              String comprehensiveQualityStates);

    @Query(value="select sum(moralPlusScore) from StudentMoralPlus where studentNumber =?1 and year =?2 and comprehensiveQualityStates = 'CQMP002' and deleted  = false ")
    Double sumMoralPlusScore(Long studentNumber,String year);
}

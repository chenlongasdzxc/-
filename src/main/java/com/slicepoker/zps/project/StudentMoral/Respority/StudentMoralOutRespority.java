package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Zps
 * @date 2019/3/14 8:51
 **/
public interface StudentMoralOutRespority extends JpaRepository<StudentMoralOut,Long>, JpaSpecificationExecutor<StudentMoralOut> {

    StudentMoralOut findByIdAndDeletedIsFalse(Long id);

    @Query(value="select sum(moralOutScore) from StudentMoralOut where studentNumber =?1 and year =?2 and comprehensiveQualityStates = 'CQMO002' and deleted = false ")
    Double sunMoralOutScore(Long studentNumber,String year);

    @Query(value="SELECT group_concat(moral_out_name)  FROM tb_student_moral_out where studentNumber =?1 and year =?2 and comprehensiveQualityStates = 'CQMO002' and deleted = false",nativeQuery=true)
    String concatMoralOutName(Long studentNumber,String year);
}

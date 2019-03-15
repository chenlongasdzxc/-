package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Zps
 * @date 2019/3/6 16:20
 **/
public interface StudentMoralPlusRespority extends JpaRepository<StudentMoralPlus,Long>, JpaSpecificationExecutor<StudentMoralPlus> {


    StudentMoralPlus findByIdAndDeletedIsFalse(Long id);

    StudentMoralPlus findByIdAndStatesAndDeletedIsFalse(Long id,String states);

    @Query(value="select sum(moralPlusScore) from StudentMoralPlus where studentNumber=?1 and year=?2 and states=?3")
    double sumPersonalMoralPlusScore(String year,String states);
}

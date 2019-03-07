package com.slicepoker.zps.project.StudentMoral.Respority;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/3/6 16:20
 **/
public interface StudentMoralPlusRespority extends JpaRepository<StudentMoralPlus,Long>, JpaSpecificationExecutor<StudentMoralPlus> {
}

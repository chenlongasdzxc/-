package com.slicepoker.zps.project.Respority;

import com.slicepoker.zps.project.Pojo.StudentMoral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/7 14:35
 **/
public interface StudentMoralRespority extends JpaRepository<StudentMoral,Long>, JpaSpecificationExecutor<StudentMoral> {


}

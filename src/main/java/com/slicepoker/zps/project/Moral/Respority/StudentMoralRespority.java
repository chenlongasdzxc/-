package com.slicepoker.zps.project.Moral.Respority;

import com.slicepoker.zps.project.Moral.Pojo.StudentMoral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2018/11/7 14:35
 **/
public interface StudentMoralRespority extends JpaRepository<StudentMoral,Long>, JpaSpecificationExecutor<StudentMoral> {


}

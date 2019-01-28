package com.slicepoker.zps.project.Checking.Respority;

import com.slicepoker.zps.project.Checking.Pojo.StudentChecking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/1/22 17:18
 **/
public interface StudentCheckingRespority extends JpaRepository<StudentChecking,Long>, JpaSpecificationExecutor<StudentChecking> {

    Page<StudentChecking> findByDeletedIsFalse(Pageable pageable);
}

package com.slicepoker.zps.project.Checking.Respority;

import com.slicepoker.zps.project.Checking.Pojo.AskForLeave;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/2/15 17:33
 **/
public interface LeavingRespority extends JpaRepository<AskForLeave,Long>, JpaSpecificationExecutor<AskForLeave> {

    Page<AskForLeave> findAllByDeletedIsFalse(Pageable pageable);

    AskForLeave findByIdAndDeletedIsFalse(Long id);

    Page<AskForLeave> findByStudentNumberAndDeletedIsFalse(Long studentNumber,Pageable pageable);
}

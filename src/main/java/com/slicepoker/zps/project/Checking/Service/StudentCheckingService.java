package com.slicepoker.zps.project.Checking.Service;

import com.slicepoker.zps.project.Checking.Pojo.StudentChecking;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/1/22 17:14
 **/
public interface StudentCheckingService {

    Commes update(StudentChecking studentChecking);

    Commes get(Pageable pageable);

    Commes findFuzzy(StudentChecking studentChecking,Pageable pageable);

    Commes getPersonalData(Long studentNumber,Pageable pageable);

    Commes getMajorData(String major,Pageable pageable);

    Commes findFuzzyByMajor(StudentChecking studentChecking,Pageable pageable);

    Commes findDeletedApply(StudentChecking studentChecking,Pageable pageable);

    Commes deleteStudentChecking(Long id);

    Commes rejectApply(Long id);
}

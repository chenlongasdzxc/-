package com.slicepoker.zps.project.StudentMoral.Service;

import com.slicepoker.zps.project.Student.Pojo.StudentMoral;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/3/14 8:52
 **/
public interface StudentMoralOutService {

    Commes add(StudentMoralOut studentMoralOut);

    Commes setStates(StudentMoralOut studentMoralOut);

    Commes delete(Long id);

    Commes findPersonalMoralOut(StudentMoralOut studentMoralOut, Pageable pageable);

    Commes findFuzzy(StudentMoralOut studentMoralOut,Pageable pageable);
}

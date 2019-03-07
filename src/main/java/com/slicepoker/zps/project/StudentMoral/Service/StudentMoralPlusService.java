package com.slicepoker.zps.project.StudentMoral.Service;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/3/6 16:20
 **/
public interface StudentMoralPlusService {

    Commes update(StudentMoralPlus studentMoralPlus);

    Commes findStudentMoralPlus(StudentMoralPlus studentMoralPlus, Pageable pageable);
}

package com.slicepoker.zps.project.StudentMoral.Service;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/3/17 21:54
 **/
public interface ApplyMoralPlusService {

    Commes applyMoral(StudentMoralPlus studentMoralPlus);

    Commes update(StudentMoralPlus studentMoralPlus);

    Commes findFuzzy(StudentMoralPlus studentMoralPlus, Pageable pageable);

    Commes cancelApply(Long id);

    Commes updateMoralOut(StudentMoralOut studentMoralOut);

    Commes findFuzzyMoralOut(StudentMoralOut studentMoralOut,Pageable pageable);

}

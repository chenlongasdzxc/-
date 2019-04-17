package com.slicepoker.zps.project.StudentMoral.Service;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeduction;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeductionTotal;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/4/15 14:37
 **/
public interface StudentMoralDeductionService {

    Commes add(StudentMoralDeduction studentMoralDeduction);

    Commes delete(Long id);

    Commes findFuzzy(StudentMoralDeduction studentMoralDeduction, Pageable pageable);

    Commes findPersonalData(StudentMoralDeduction studentMoralDeduction,Pageable pageable);

    Commes update(Long id,String states);

    Commes findFuzzyTotal(StudentMoralDeductionTotal studentMoralDeductionTotal,Pageable pageable);
}

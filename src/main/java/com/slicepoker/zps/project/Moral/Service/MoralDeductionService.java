package com.slicepoker.zps.project.Moral.Service;

import com.slicepoker.zps.project.Moral.Pojo.MoralDeduction;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2018/11/28 16:06
 **/
public interface MoralDeductionService {

    Commes add(MoralDeduction moralDeduction);

    Commes delete(Long id);

    Commes findAll();

    Commes findByType(String moralDeductionType);

    Commes findMoralDeduction();

    Commes findMoralDeductionType();

    Commes findFuzzy(MoralDeduction moralDeduction, Pageable pageable);

    MoralDeduction findByMoralDeductionType(String moralDeductionType);


}

package com.slicepoker.zps.project.Moral.Service;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2018/11/29 9:15
 **/
public interface MoralOutService {

    Commes update(MoralOut moralOut);

    Commes delete(Long id);

    Commes findAll();

    Commes findByType(String moralOutType);

    Commes findFuzzy(MoralOut moralOut, Pageable pageable);

    Commes findMoralOutType();

    Commes findMoralOutName(String moralOutType);
}

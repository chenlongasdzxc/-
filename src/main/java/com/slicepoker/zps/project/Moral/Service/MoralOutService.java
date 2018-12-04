package com.slicepoker.zps.project.Moral.Service;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2018/11/29 9:15
 **/
public interface MoralOutService {

    Commes add(MoralOut moralOut);

    Commes delete(Long id);

    Commes findAll();

    Commes findByType(String moralOutType);
}

package com.slicepoker.zps.project.Moral.Service;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2018/11/28 14:11
 **/
public interface MoralPlusService {

    Commes add(MoralPlus moralPlus);

    Commes delete(Long id);

    Commes findAll();

    Commes find();
}

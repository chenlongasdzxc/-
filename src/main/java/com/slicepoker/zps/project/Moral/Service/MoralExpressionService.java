package com.slicepoker.zps.project.Moral.Service;

import com.slicepoker.zps.project.Moral.Pojo.MoralExpression;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpressionTotal;
import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2018/11/26 17:24
 **/
public interface MoralExpressionService {

    Commes add(MoralExpression moralExpression);

    Commes delete(Long id);

    Commes findAll();

    Commes findMoralExpressionName();


}

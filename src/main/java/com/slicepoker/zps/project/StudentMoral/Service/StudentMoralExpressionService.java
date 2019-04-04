package com.slicepoker.zps.project.StudentMoral.Service;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpression;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpressionTotal;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/3/22 16:46
 **/
public interface StudentMoralExpressionService {

    Commes findPersonalMoralExpression(StudentMoralExpression studentMoralExpression);

    Commes update(StudentMoralExpression studentMoralExpression);

    Commes findFuzzy(StudentMoralExpression studentMoralExpression, Pageable pageable);

    Commes checkMoralExpression(StudentMoralExpression studentMoralExpression);

    Commes findMoralExpressionTotal(StudentMoralExpressionTotal studentMoralExpressionTotal,Pageable pageable);

    Commes deleteMoralExpression(Long id);

    Commes editMoralExpression(StudentMoralExpression studentMoralExpression);
}

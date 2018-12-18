package com.slicepoker.zps.project.Student.Controller;

import com.slicepoker.zps.project.Student.Pojo.StudentMoralExpression;
import com.slicepoker.zps.project.Student.Service.StudentMoralExpressionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2018/12/4 16:15
 **/
@RestController
@RequestMapping("/Student")
public class StudentMoralExpressionController {

    @Autowired
    private StudentMoralExpressionService moralExpressionService;

    @PostMapping("/update")
    public Commes undate(StudentMoralExpression studentMoralExpression){
        return moralExpressionService.update(studentMoralExpression);
    }
}

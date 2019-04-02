package com.slicepoker.zps.project.StudentMoral.Controller;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpression;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpressionTotal;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralExpressionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2019/3/22 16:49
 **/
@RestController
@RequestMapping("/studentMoralExpression")
public class StudentMoralExpressionController {

    @Autowired
    private StudentMoralExpressionService studentMoralExpressionService;

    @GetMapping("/findPersonal")
    public Commes findPersonalMoralExpression(StudentMoralExpression studentMoralExpression){
        return studentMoralExpressionService.findPersonalMoralExpression(studentMoralExpression);
    }

    @PostMapping("/update")
    @Transactional(rollbackOn = Exception.class)
    public Commes update(@RequestBody List<StudentMoralExpression> saveList){
        Boolean success = true;
        Commes commes;
        for (StudentMoralExpression studentMoralExpression:saveList){
            if (success){
                commes = studentMoralExpressionService.update(studentMoralExpression);
                if (!Objects.equals(commes.getCode(),"200"))
                    success = false;
            }else {
                break;
            }
        }
        return success ? Commes.successMes() : Commes.errorMes("401","保存失败");
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentMoralExpression studentMoralExpression, Pageable pageable){
        return studentMoralExpressionService.findFuzzy(studentMoralExpression, pageable);
    }

    @GetMapping("/checkMoralExpression")
    public Commes checkMoralExpression(StudentMoralExpression studentMoralExpression){
        return studentMoralExpressionService.checkMoralExpression(studentMoralExpression);
    }

    @GetMapping("/findMoralExpressionTotal")
    public Commes findMoralExpressionTotal(StudentMoralExpressionTotal studentMoralExpressionTotal,Pageable pageable){
        return studentMoralExpressionService.findMoralExpressionTotal(studentMoralExpressionTotal,pageable);
    }

    @GetMapping("/deleteMoralExpression")
    public Commes deleteMoralExpression(StudentMoralExpression studentMoralExpression){
        return studentMoralExpressionService.deleteMoralExpression(studentMoralExpression.getId());
    }


}

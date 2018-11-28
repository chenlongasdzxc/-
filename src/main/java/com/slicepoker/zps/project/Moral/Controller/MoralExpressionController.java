package com.slicepoker.zps.project.Moral.Controller;

import com.slicepoker.zps.project.Moral.Pojo.MoralExpression;
import com.slicepoker.zps.project.Moral.Service.MoralExpressionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2018/11/27 14:06
 **/
@RestController
public class MoralExpressionController {

    @Autowired
    private MoralExpressionService moralExpressionService;

    @PostMapping("/addExpression")
    public Commes add(MoralExpression moralExpression){
        return moralExpressionService.add(moralExpression);
    }

    @PostMapping("/deExpression")
    public Commes delete(Long id){
        return moralExpressionService.delete(id);
    }

    @GetMapping("/findExpression")
    public Commes findAll(){
        return moralExpressionService.findAll();
    }
}

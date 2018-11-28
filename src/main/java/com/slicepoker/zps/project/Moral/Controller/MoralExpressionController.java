package com.slicepoker.zps.project.Moral.Controller;

import com.slicepoker.zps.project.Moral.Pojo.MoralExpression;
import com.slicepoker.zps.project.Moral.Service.MoralExpressionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2018/11/27 14:06
 **/
@RestController
@RequestMapping("/MoralExpression")
public class MoralExpressionController {

    @Autowired
    private MoralExpressionService moralExpressionService;

    @PostMapping("/add")
    public Commes add(@RequestBody MoralExpression moralExpression){
        return moralExpressionService.add(moralExpression);
    }

    @PostMapping("/delete")
    public Commes delete(@RequestParam Long id){
        return moralExpressionService.delete(id);
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return moralExpressionService.findAll();
    }
}

package com.slicepoker.zps.project.Moral.Controller;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import com.slicepoker.zps.project.Moral.Service.MoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2018/11/28 14:28
 **/
@RestController
@RequestMapping("/MoralPlus")
public class MoralPlusController {

    @Autowired
    private MoralPlusService moralPlusService;

    @PostMapping("/add")
    public Commes add(MoralPlus moralPlus){
        return moralPlusService.add(moralPlus);
    }

    @GetMapping("/delete")
    public Commes deleted(Long id){
        return moralPlusService.delete(id);
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return moralPlusService.findAll();
    }

    @GetMapping("/find")
    public Commes find(){
        return moralPlusService.find();
    }
}

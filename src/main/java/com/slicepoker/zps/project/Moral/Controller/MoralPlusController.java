package com.slicepoker.zps.project.Moral.Controller;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import com.slicepoker.zps.project.Moral.Service.MoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2018/11/28 14:28
 **/
@RestController
public class MoralPlusController {

    @Autowired
    private MoralPlusService moralPlusService;

    @PostMapping("/addMoralPlus")
    public Commes add(MoralPlus moralPlus){
        return moralPlusService.add(moralPlus);
    }

    @GetMapping("/deleteMoralPlus")
    public Commes deleted(Long id){
        return moralPlusService.delete(id);
    }

    @GetMapping("/findAllMoralPlus")
    public Commes findAll(){
        return moralPlusService.findAll();
    }

    @GetMapping("/findMoralPlus")
    public Commes find(){
        return moralPlusService.find();
    }
}

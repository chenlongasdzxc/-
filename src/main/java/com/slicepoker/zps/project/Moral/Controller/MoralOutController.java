package com.slicepoker.zps.project.Moral.Controller;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import com.slicepoker.zps.project.Moral.Service.MoralOutService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2018/11/29 14:06
 **/
@RestController
@RequestMapping("/moralOut")
public class MoralOutController {

    @Autowired
    private MoralOutService moralOutService;

    @PostMapping("/add")
    public Commes add(MoralOut moralOut){
        return moralOutService.add(moralOut);
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return moralOutService.findAll();
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return moralOutService.delete(id);
    }

    @GetMapping("findByType")
    public Commes findByType(String moralOutType){
        return moralOutService.findByType(moralOutType);
    }

}

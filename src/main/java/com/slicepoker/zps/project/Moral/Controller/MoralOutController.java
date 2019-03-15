package com.slicepoker.zps.project.Moral.Controller;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import com.slicepoker.zps.project.Moral.Service.MoralOutService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @PostMapping("/update")
    public Commes update(@RequestBody MoralOut moralOut){
        return moralOutService.update(moralOut);
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return moralOutService.findAll();
    }

    @GetMapping("/delete")
    public Commes delete(MoralOut moralOut){
        return moralOutService.delete(moralOut.getId());
    }

    @GetMapping("findByType")
    public Commes findByType(String moralOutType){
        return moralOutService.findByType(moralOutType);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(MoralOut moralOut, Pageable pageable){
        return moralOutService.findFuzzy(moralOut, pageable);
    }

    @GetMapping("/findMoralOutType")
    public Commes findMoralOutType(){
        return moralOutService.findMoralOutType();
    }

    @GetMapping("/findMoralOutName")
    public Commes findMoralOutName(MoralOut moralOut){
        return moralOutService.findMoralOutName(moralOut.getMoralOutType());
    }

}

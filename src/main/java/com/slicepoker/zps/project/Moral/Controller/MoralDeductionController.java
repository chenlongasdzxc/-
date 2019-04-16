package com.slicepoker.zps.project.Moral.Controller;

import com.slicepoker.zps.project.Moral.Pojo.MoralDeduction;
import com.slicepoker.zps.project.Moral.Service.MoralDeductionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2018/11/28 16:44
 **/
@RestController
@RequestMapping("/moralDeduction")
public class MoralDeductionController {

    @Autowired
    private MoralDeductionService moralDeductionService;

    @PostMapping("/add")
    public Commes add(@RequestBody MoralDeduction moralDeduction){
        return moralDeductionService.add(moralDeduction);
    }

    @PostMapping("/findByType")
    private Commes findByType(String moralDeductionType){
        return moralDeductionService.findByType(moralDeductionType);
    }

    @GetMapping("/find")
    public Commes find(){
        return moralDeductionService.findMoralDeduction();
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return moralDeductionService.findAll();
    }

    @PostMapping("/delete")
    public Commes delete(@RequestBody MoralDeduction moralDeduction){
        return moralDeductionService.delete(moralDeduction.getId());
    }

    @GetMapping("/findType")
    public Commes findType(){
        return moralDeductionService.findMoralDeductionType();
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(MoralDeduction moralDeduction, Pageable pageable){
        return moralDeductionService.findFuzzy(moralDeduction, pageable);
    }

}

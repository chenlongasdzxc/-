package com.slicepoker.zps.project.StudentMoral.Controller;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.StudentMoral.Service.ApplyMoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2019/3/18 15:24
 **/
@RestController
@RequestMapping("/apply")
public class ApplyComprehensiveController {

    @Autowired
    private ApplyMoralPlusService applyMoralPlusService;

    @GetMapping("/updateMoralPlus")
    public Commes updateMoralPlus(StudentMoralPlus studentMoralPlus){
        return applyMoralPlusService.updateMoralPlus(studentMoralPlus);
    }



    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentMoralPlus studentMoralPlus, Pageable pageable){
        return applyMoralPlusService.findFuzzy(studentMoralPlus,pageable);
    }


    /**
     * @param id
     * @description 取消综合素质德育加分申请
     * **/
    @GetMapping("/cancelApply")
    public Commes cancelApply(Long id){
        return applyMoralPlusService.cancelApply(id);
    }

    /**
     * @param studentMoralOut
     * @description 更新综合素质课外加分申请
     * **/
    @GetMapping("/updateMoralOut")
    public Commes updateMoralOut(StudentMoralOut studentMoralOut){
        return applyMoralPlusService.updateMoralOut(studentMoralOut);
    }

    /**
     * @param studentMoralOut
     * @param pageable
     * @description 模糊查询综合素质课外加分
     * **/
    @GetMapping("/findFuzzyMoralOut")
    public Commes findFuzzyMoralOut(StudentMoralOut studentMoralOut,Pageable pageable){
        return applyMoralPlusService.findFuzzyMoralOut(studentMoralOut, pageable);
    }
}

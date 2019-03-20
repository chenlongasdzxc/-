package com.slicepoker.zps.project.StudentMoral.Controller;

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

    @GetMapping("/update")
    public Commes update(StudentMoralPlus studentMoralPlus){
        return applyMoralPlusService.update(studentMoralPlus);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentMoralPlus studentMoralPlus, Pageable pageable){
        return applyMoralPlusService.findFuzzy(studentMoralPlus,pageable);
    }

    @GetMapping("/cancelApply")
    public Commes cancelApply(Long id){
        return applyMoralPlusService.cancelApply(id);
    }
}

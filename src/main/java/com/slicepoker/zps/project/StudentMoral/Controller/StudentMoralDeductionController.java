package com.slicepoker.zps.project.StudentMoral.Controller;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeduction;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralDeductionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/4/15 14:39
 **/
@RestController
@RequestMapping("/studentMoralDeduction")
public class StudentMoralDeductionController {

    @Autowired
    private StudentMoralDeductionService studentMoralDeductionService;

    @PostMapping("/add")
    public Commes add(@RequestBody StudentMoralDeduction studentMoralDeduction){
        return studentMoralDeductionService.add(studentMoralDeduction);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentMoralDeduction studentMoralDeduction, Pageable pageable){
        return studentMoralDeductionService.findFuzzy(studentMoralDeduction, pageable);
    }

}

package com.slicepoker.zps.project.StudentMoral.Controller;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeduction;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeductionTotal;
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

    @GetMapping("/findPersonal")
    public Commes findPersonal(StudentMoralDeduction studentMoralDeduction,Pageable pageable){
        return studentMoralDeductionService.findPersonalData(studentMoralDeduction, pageable);
    }

    @GetMapping("/update/{id}/{states}")
    public Commes update(@PathVariable(name="id") Long id,
                         @PathVariable(name="states") String states){
        return studentMoralDeductionService.update(id, states);
    }

    @GetMapping("/delete/{id}")
    public Commes delete(@PathVariable(name="id") Long id){
        return studentMoralDeductionService.delete(id);
    }

    @GetMapping("/findFuzzyTotal")
    public Commes findFuzzyTotal(StudentMoralDeductionTotal studentMoralDeductionTotal,Pageable pageable){
        return studentMoralDeductionService.findFuzzyTotal(studentMoralDeductionTotal, pageable);
    }

}

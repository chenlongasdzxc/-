package com.slicepoker.zps.project.StudentMoral.Controller;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/3/6 16:19
 **/
@RestController
@RequestMapping("/studentMoralPlus")
public class StudentMoralPlusController {

    @Autowired
    private StudentMoralPlusService studentMoralPlusService;

    @PostMapping("/add")
    public Commes add(@RequestBody StudentMoralPlus studentMoralPlus){
        return studentMoralPlusService.add(studentMoralPlus);
    }

    @GetMapping("/findPersonal")
    public Commes findStudentMoralPlus(StudentMoralPlus studentMoralPlus, Pageable pageable){
        return studentMoralPlusService.findStudentMoralPlus(studentMoralPlus, pageable);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentMoralPlus studentMoralPlus,Pageable pageable){
        return studentMoralPlusService.findFuzzy(studentMoralPlus, pageable);
    }

    @PostMapping("/setStates")
    public Commes setStates(@RequestBody StudentMoralPlus studentMoralPlus){
        return studentMoralPlusService.setStates(studentMoralPlus);
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return studentMoralPlusService.delete(id);
    }

    @PostMapping("/update")
    public Commes update(@RequestBody StudentMoralPlus studentMoralPlus){
        return studentMoralPlusService.update(studentMoralPlus);
    }

    @GetMapping("/search")
    public Commes search(StudentMoralPlus studentMoralPlus,Pageable pageable){
        return studentMoralPlusService.search(studentMoralPlus, pageable);
    }


}

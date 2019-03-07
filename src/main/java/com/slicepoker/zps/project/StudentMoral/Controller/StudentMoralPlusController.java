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

    @PostMapping("/update")
    public Commes update(@RequestBody StudentMoralPlus studentMoralPlus){
        return studentMoralPlusService.update(studentMoralPlus);
    }

    @GetMapping("/findPersonal")
    public Commes findStudentMoralPlus(StudentMoralPlus studentMoralPlus, Pageable pageable){
        return studentMoralPlusService.findStudentMoralPlus(studentMoralPlus, pageable);
    }
}

package com.slicepoker.zps.project.Grade.Controller;

import com.slicepoker.zps.project.Grade.Pojo.StudentGPA;
import com.slicepoker.zps.project.Grade.Service.StudentGPAService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/4/23 14:38
 **/
@RestController
@RequestMapping("/studentGPA")
public class StudentGPAController {

    @Autowired
    private StudentGPAService studentGPAService;


    @PostMapping("/update")
    public Commes update(@RequestBody StudentGPA studentGPA){
        return studentGPAService.update(studentGPA);
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return studentGPAService.findAll();
    }
}

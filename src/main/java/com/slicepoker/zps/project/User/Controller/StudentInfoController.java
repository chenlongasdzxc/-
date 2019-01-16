package com.slicepoker.zps.project.User.Controller;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import com.slicepoker.zps.project.User.Service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2018/10/26 17:22
 **/
@RestController
@RequestMapping("/studentInfo")
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    @GetMapping("/findFuzzy")
    public Commes findFuzzy( StudentInformation studentInformation){
        return studentInfoService.findFuzzy(studentInformation);
    }

    @PostMapping("/update")
    public Commes updateInfo(@RequestBody StudentInformation studentInformation){
        return studentInfoService.updateInfo(studentInformation);
    }

    @GetMapping("/get")
    public Commes getInfo(String userName){
        return studentInfoService.findStudentByStudentNumber(userName);
    }

    @GetMapping("/getFileCard")
    public Commes getFileCard(){
        return studentInfoService.findFileCard();
    }


}

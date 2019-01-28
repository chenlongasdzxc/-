package com.slicepoker.zps.project.Checking.Controller;

import com.slicepoker.zps.project.Checking.Pojo.StudentChecking;
import com.slicepoker.zps.project.Checking.Service.StudentCheckingService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2019/1/22 17:10
 **/
@RestController
@RequestMapping("/studentChecking")
public class StudentCheckingController {

    @Autowired
    private StudentCheckingService studentCheckingService;

    @PostMapping("/update")
    public Commes update(@RequestBody StudentChecking studentChecking){
        return studentCheckingService.update(studentChecking);
    }

    @GetMapping("/get")
    public Commes getCheckingIn(Pageable pageable ){
       return studentCheckingService.get(pageable);
    }
}

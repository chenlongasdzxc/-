package com.slicepoker.zps.project.Checking.Controller;

import com.slicepoker.zps.project.Checking.Pojo.StudentChecking;
import com.slicepoker.zps.project.Checking.Service.StudentCheckingService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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


    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentChecking studentChecking,Pageable pageable){
        return studentCheckingService.findFuzzy(studentChecking, pageable);
    }

    @GetMapping("/getPersonalData")
    public Commes getPersonalData(Long studentNumber,Pageable pageable){
        return studentCheckingService.getPersonalData(studentNumber, pageable);

    }

    @GetMapping("/getMajorData")
    public Commes getMajorData(String major,Pageable pageable){
       return studentCheckingService.getMajorData(major, pageable);
    }

    @GetMapping("findFuzzyByMajor")
    public Commes findFuzzyByMajor(StudentChecking studentChecking, Pageable pageable){
        if (studentChecking.getMajor()!=null && !"".equals(studentChecking.getMajor())){
            return studentCheckingService.findFuzzyByMajor(studentChecking, pageable);
        }else {
            return Commes.errorMes("403","major为null");
        }
    }

    @GetMapping("/findDeleteApplyData")
    public Commes findDeleteApplyData(StudentChecking studentChecking,Pageable pageable){
        return studentCheckingService.findDeletedApply(studentChecking, pageable);
    }

    @GetMapping("/delete")
    public Commes delete(Long id){
        return studentCheckingService.deleteStudentChecking(id);
    }

    @GetMapping("/rejectApply")
    public Commes rejectApply(Long id){
        return studentCheckingService.rejectApply(id);
    }
}

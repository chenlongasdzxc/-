package com.slicepoker.zps.project.User.Controller;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import com.slicepoker.zps.project.User.Service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2018/10/26 17:22
 **/
@RestController
public class StudentInfoController {

    @Autowired
    private StudentInfoService studentInfoService;

    @PostMapping("/findFuzzy")
    public Commes findFuzzy(Long studentCode, String studentName, Integer sex, String studentClass,String roomNumber, Pageable pageable){
        return studentInfoService.findFuzzy(studentCode,studentName,sex,studentClass,roomNumber,pageable);
    }

    /*@GetMapping("/findRoom")
    public Commes findRoom(Pageable pageable){
        return studentInfoService.findRoom(pageable);
    }*/

    @PostMapping("/updateInfo")
    public Commes updateInfo(StudentInformation studentInformation){
        return studentInfoService.updateInfo(studentInformation);
    }
}

package com.slicepoker.zps.project.Controller;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Service.StudentInfoService;
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
    public Commes findFuzzy(Long studentCode, String studentName, Integer sex, String studentClass, Pageable pageable){
        return studentInfoService.findFuzzy(studentCode,studentName,sex,studentClass,pageable);
    }
}

package com.slicepoker.zps.project.Student.Controller;

import com.slicepoker.zps.project.Student.Service.StudentMoralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2019/3/11 16:08
 **/
@RestController
@RequestMapping("/studentMoral")
public class StudentMoralController {

    @Autowired
    private StudentMoralService studentMoralService;
}

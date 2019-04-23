package com.slicepoker.zps.project.Grade.Controller;

import com.slicepoker.zps.project.Grade.Pojo.StudentGrade;
import com.slicepoker.zps.project.Grade.Service.StudentGradeService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Zps
 * @date 2019/4/22 16:21
 **/
@RequestMapping("/studentGrade")
@RestController
public class StudentGradeController {

    @Autowired
    private StudentGradeService studentGradeService;

    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){
        studentGradeService.exportStudentGradeExcel(response);
    }

    @PostMapping("/importExcel")
    public Commes importExcel(MultipartFile file){
        return studentGradeService.importExcel(file);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(StudentGrade studentGrade, Pageable pageable){
        return studentGradeService.findFuzzy(studentGrade, pageable);
    }

    @GetMapping("/update")
    public Commes update(StudentGrade studentGrade){
        return studentGradeService.update(studentGrade);
    }

    @GetMapping("/findGradeNameList/{gradeYear}/{major}")
    public Commes findGradeNameList(@PathVariable(name="gradeYear") String gradeYear,
                                    @PathVariable(name="major") String major){
        return studentGradeService.findGradeNameList(gradeYear, major);
    }

}

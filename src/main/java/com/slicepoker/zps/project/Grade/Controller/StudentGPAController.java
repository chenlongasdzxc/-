package com.slicepoker.zps.project.Grade.Controller;

import com.slicepoker.zps.project.Grade.Pojo.Gpa;
import com.slicepoker.zps.project.Grade.Pojo.StudentGPA;
import com.slicepoker.zps.project.Grade.Respority.GpaRespority;
import com.slicepoker.zps.project.Grade.Respority.StudentGPARespority;
import com.slicepoker.zps.project.Grade.Service.StudentGPAService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2019/4/23 14:38
 **/
@RestController
@RequestMapping("/studentGPA")
public class StudentGPAController {

    @Autowired
    private StudentGPAService studentGPAService;

    @Autowired
    private StudentGPARespority studentGPARespority;

    @Autowired
    private GpaRespority gpaRespority;


    @PostMapping("/update/{year}/{major}/{gpaScore}/{gpaNameCount}")
    public Commes update(@PathVariable(name="year") String year,
                         @PathVariable(name="major") String major,
                         @PathVariable(name="gpaScore") double gpaScore,
                         @PathVariable(name="gpaNameCount") double gpaNameCount,
                         @RequestBody List<StudentGPA> params){
        Boolean success = true;
        Commes commes;
        if (params.size()>0){
            studentGPAService.deletedList(major, year);
            for (StudentGPA studentGPA:params) {
                if (success){
                    studentGPA.setGpaScore(gpaScore);
                    studentGPA.setGpaNameCount(gpaNameCount);
                    studentGPA.setYear(year);
                    studentGPA.setMajor(major);
                    commes = studentGPAService.update(studentGPA);
                    if (!Objects.equals(commes.getCode(),"200"))
                        success = false;
                }else {
                    break;
                }
            }
        }
        setGpa(year,major,gpaScore);
        return success ? Commes.successMes() :Commes.errorMes("401","保存失败");
    }

    @GetMapping("/findAll")
    public Commes findAll(){
        return studentGPAService.findAll();
    }

    @GetMapping("/findAllGpa")
    public Commes findAllGpa(){
        return studentGPAService.findAllGpa();
    }

    private void setGpa(String year,String major,double score){
        Gpa gpa = new Gpa();
        gpa.setMajor(major);
        gpa.setGpaScore(score);
        gpa.setYear(year);
        gpa.setGpaNameList(studentGPARespority.selectGpaName(year, major));
        gpa.setCreditCount(studentGPARespority.creditSum(major,year));
        gpaRespority.save(gpa);
    }
}

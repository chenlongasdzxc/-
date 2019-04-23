package com.slicepoker.zps.project.Grade.Service.Impl;

import com.slicepoker.zps.project.Grade.Pojo.StudentGPA;
import com.slicepoker.zps.project.Grade.Respority.StudentGPARespority;
import com.slicepoker.zps.project.Grade.Respority.StudentGradeRespority;
import com.slicepoker.zps.project.Grade.Service.StudentGPAService;
import com.slicepoker.zps.project.Grade.Service.StudentGradeService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Zps
 * @date 2019/4/23 14:36
 **/
@Service
public class StudentGPAServiceImpl implements StudentGPAService {

    @Autowired
    private StudentGPARespority studentGPARespority;

    /**
     * @param studentGPA
     * @description 更新
     * ***/
    @Override
    public Commes update(StudentGPA studentGPA) {
        try {
            StudentGPA studentGPA1 = studentGPARespority.findByYearAndMajorAndDeletedIsFalse(studentGPA.getYear(),studentGPA.getMajor());
            if (studentGPA1==null){
                studentGPA.setGpaName(studentGPA.getGpaName().substring(1,studentGPA.getGpaName().length()-1));
                return Commes.success(studentGPARespority.save(studentGPA));
            }else {
                studentGPA1.setGpaScore(studentGPA.getGpaScore());
                studentGPA1.setGpaName(studentGPA.getGpaName().substring(1,studentGPA.getGpaName().length()-1));
                studentGPA1.setGpaNameCount(studentGPA.getGpaNameCount());
                studentGPA1.setMajor(studentGPA.getMajor());
                studentGPA1.setYear(studentGPA.getYear());
                return Commes.success(studentGPARespority.save(studentGPA1));
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }


    @Override
    public Commes findAll() {
        try {
            List<StudentGPA> list = studentGPARespority.findByDeletedIsFalse();
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","查找数据失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}

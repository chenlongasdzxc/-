package com.slicepoker.zps.project.Grade.Service.Impl;

import com.slicepoker.zps.project.Grade.Pojo.Gpa;
import com.slicepoker.zps.project.Grade.Pojo.StudentGPA;
import com.slicepoker.zps.project.Grade.Respority.GpaRespority;
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

    @Autowired
    private GpaRespority gpaRespority;

    @Autowired
    private StudentGradeRespority studentGradeRespority;


    /**
     * @param studentGPA
     * @description 更新
     * ***/
    @Override
    public Commes update(StudentGPA studentGPA) {
        try {
            if (studentGPA!=null && !"".equals(studentGPA)){
                double score = studentGradeRespority.findGradeScore(studentGPA.getYear(),studentGPA.getGpaName());
                studentGPA.setCredit(score);
                studentGPARespority.save(studentGPA);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","对象为空");
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


    /**
     * @param id
     * @description 删除
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            StudentGPA studentGPA = studentGPARespority.findByIdAndDeletedIsFalse(id);
            if (studentGPA!=null){
                studentGPA.setDeleted(true);
                studentGPARespority.save(studentGPA);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }


    @Override
    public void deletedList(String major, String year) {
        try {
            List<StudentGPA> list = studentGPARespority.findByMajorAndYearAndDeletedIsFalse(major, year);
            if (list.size()>0){
                for (StudentGPA studentGPA:list) {
                    studentGPA.setDeleted(true);
                    studentGPARespority.save(studentGPA);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * @param
     * **/
    @Override
    public Commes findAllGpa() {
        try {
            List<Gpa> list = gpaRespority.findAllByDeletedIsFalse();
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}

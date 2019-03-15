package com.slicepoker.zps.project.Student.Service.Impl;

import com.slicepoker.zps.project.Student.Pojo.StudentMoral;
import com.slicepoker.zps.project.Student.Respority.StudentMoralRespority;
import com.slicepoker.zps.project.Student.Service.StudentMoralService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zps
 * @date 2019/3/11 16:06
 **/
@Service
public class StudentMoralServiceImpl implements StudentMoralService {

    @Autowired
    private StudentMoralRespority studentMoralRespority;


    /**
     * @description初始化德育分表
     * **/
    @Override
    public void setStudentMoral(Long studentNumber,String studentName) {

            if (studentNumber!=null) {
                StudentMoral studentMoral=studentMoralRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
                if (studentMoral == null) {
                    studentMoral = new StudentMoral();
                    studentMoral.setStudentNumber(studentNumber);
                    studentMoral.setStudentName(studentName);
                    studentMoralRespority.saveAndFlush(studentMoral);
                }
            }
    }
}

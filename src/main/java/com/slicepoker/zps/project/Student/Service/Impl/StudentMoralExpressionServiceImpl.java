package com.slicepoker.zps.project.Student.Service.Impl;

import com.slicepoker.zps.project.Student.Pojo.StudentMoralExpression;
import com.slicepoker.zps.project.Student.Respority.StudentMoralExpressionRespority;
import com.slicepoker.zps.project.Student.Service.StudentMoralExpressionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Zps
 * @date 2018/12/4 16:03
 **/
@Service
public class StudentMoralExpressionServiceImpl implements StudentMoralExpressionService {

    @Autowired
    private StudentMoralExpressionRespority studentMoralExpressionRespority;


    /**
     * @param studentMoralExpression
     * 新增或编辑
     * **/
    @Override
    public Commes update(StudentMoralExpression studentMoralExpression) {
        try {
            studentMoralExpression.setCreateDate(new Date());
            return Commes.success(studentMoralExpressionRespority.save(studentMoralExpression));
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","编辑失败");
        }
    }
}

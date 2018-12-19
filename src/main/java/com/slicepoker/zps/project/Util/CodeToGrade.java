package com.slicepoker.zps.project.Util;

import org.springframework.stereotype.Service;

/**
 * @author Zps
 * @date 2018/11/20 14:07
 **/
@Service
public class CodeToGrade {


    /**
     * @param studentCode
     *根据学号设置年级
     * **/
    public Long codeToGrade(Long studentCode){
            double b = (double) studentCode;
            double c ;
            if (studentCode!=null){
                double a = Math.pow(10,8);
                c = b/a;
                Long grade = Math.round(c);
                return grade;
            }else {
                return null;
            }
    }

    /**
     * @param grade
     * 根据学号设置专业
     * **/
    public String codeToClass(Long grade){
        String stuClass;
        String studentCode =Long.toString(grade);
        studentCode=studentCode.substring(4);
        studentCode=studentCode.substring(0,4);
        double a = (double) grade;
        double b = Math.pow(10,4);
        if (grade!=null&&"1080".equals(studentCode)){
            Long c = Math.round((a%b)/1000);
            if (c==4){
                stuClass="软件工程";
               return stuClass;
            }
            if (c==1){
                stuClass="计算机科学与技术";
                return stuClass;
            }
            if (c==2){
                stuClass="信息与计算科学";
                return stuClass;
            }
            if (c==3){
                stuClass="网络工程";
                return stuClass;
            }
        }
        return null;

    }
}

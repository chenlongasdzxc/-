package com.slicepoker.zps.project.Util;

import org.springframework.stereotype.Service;

/**
 * @author Zps
 * @date 2018/11/20 14:07
 **/
@Service
public class CodeToGrade {


    /**
     *根据学号设置年级
     * **/
    public Long codeToGrade(Long studentCode){
        try {
            double b = (double) studentCode;
            double c ;
            if (studentCode!=null){
                double a = Math.pow(10,8);
                c = b/a;
                Long grade = Math.round(c);
                System.out.println(grade);
                return grade;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据学号设置专业
     * **/
    public String codeToClass(Long grade){
        String stuClass;
        double a = (double) grade;
        double b = Math.pow(10,4);
        if (grade!=null){
            Long c = Math.round((a%b)/1000);
            System.out.println(c);
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

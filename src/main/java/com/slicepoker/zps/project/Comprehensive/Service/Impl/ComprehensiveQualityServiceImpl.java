package com.slicepoker.zps.project.Comprehensive.Service.Impl;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import com.slicepoker.zps.project.Comprehensive.Respority.ComPrehensiveQualityRespority;
import com.slicepoker.zps.project.Comprehensive.Service.ComprehensiveQualityService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zps
 * @date 2019/4/16 16:20
 **/
@Service
public class ComprehensiveQualityServiceImpl implements ComprehensiveQualityService {

    @Autowired
    private ComPrehensiveQualityRespority comPrehensiveQualityRespority;


    /**
     * @param comprehensiveQuality
     * @description 更新综合素质
     * **/
    @Override
    public Commes update(ComprehensiveQuality comprehensiveQuality) {
        try {
            if (comprehensiveQuality.getMoralDeductionNameList()!=null && !"".equals(comprehensiveQuality.getMoralDeductionNameList())){
                setMoralDeduction(comprehensiveQuality);
            }
            if (comprehensiveQuality.getMoralExpressionNameList()!=null && !"".equals(comprehensiveQuality.getMoralExpressionNameList())){
                setMoralExpression(comprehensiveQuality);
            }
            if (comprehensiveQuality.getMoralOutNameList()!=null && !"".equals(comprehensiveQuality.getMoralOutNameList())){
                setMoralOut(comprehensiveQuality);
            }
            if (comprehensiveQuality.getMoralPlusNameList()!=null && !"".equals(comprehensiveQuality.getMoralPlusNameList())){
                setMoralPlus(comprehensiveQuality);
            }
            return Commes.successMes();
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    private ComprehensiveQuality findComprehensiveQuality(ComprehensiveQuality comprehensiveQuality){
        ComprehensiveQuality comprehensiveQuality1 = comPrehensiveQualityRespority.findByStudentNumberAndComprehensiveQualityYearAndDeletedIsFalse(comprehensiveQuality.getStudentNumber(),comprehensiveQuality.getComprehensiveQualityYear());
        //不为NULL，返回这个实体
        if (comprehensiveQuality1!=null){
            return comprehensiveQuality1;
        }else { //创建这个实体
            ComprehensiveQuality comprehensiveQuality2 = new ComprehensiveQuality();
            comprehensiveQuality2.setStudentName(comprehensiveQuality.getStudentName());  //名称
            comprehensiveQuality2.setStudentClass(comprehensiveQuality.getStudentClass());  //班级
            comprehensiveQuality2.setMajor(comprehensiveQuality.getMajor());  //专业
            comprehensiveQuality2.setGrade(comprehensiveQuality.getGrade()); //年级
            comprehensiveQuality2.setStudentNumber(comprehensiveQuality.getStudentNumber());  //学号
            comprehensiveQuality2.setComprehensiveQualityYear(comprehensiveQuality.getComprehensiveQualityYear());  //年度
            comPrehensiveQualityRespority.save(comprehensiveQuality2);
            return comprehensiveQuality2;
        }
    }


    /**
     * @param comprehensiveQuality
     * @description 往综合素质分中填写德育表现
     * **/
    private void setMoralExpression(ComprehensiveQuality comprehensiveQuality){
        ComprehensiveQuality comprehensiveQuality1 = findComprehensiveQuality(comprehensiveQuality);
        assert comprehensiveQuality1 != null;
        comprehensiveQuality1.setMoralExpressionNameList(comprehensiveQuality.getMoralExpressionNameList());
        comprehensiveQuality1.setMoralExpressionScore(comprehensiveQuality.getMoralExpressionScore());
        comprehensiveQuality1.setStates(comprehensiveQuality.getStates());
        comPrehensiveQualityRespority.save(comprehensiveQuality1);
    }


    /**
     * @param comprehensiveQuality
     * @description 往综合素质分钟填写德育减分
     * **/
    private void setMoralDeduction(ComprehensiveQuality comprehensiveQuality){
        ComprehensiveQuality comprehensiveQuality1 = findComprehensiveQuality(comprehensiveQuality);
        assert comprehensiveQuality1 != null;
        comprehensiveQuality1.setMoralDeductionNameList(comprehensiveQuality.getMoralDeductionNameList());
        comprehensiveQuality1.setMoralDeductionScore(comprehensiveQuality.getMoralDeductionScore());
        comprehensiveQuality1.setStates(comprehensiveQuality.getStates());
        comPrehensiveQualityRespority.save(comprehensiveQuality1);
    }

    /**
     * @param comprehensiveQuality
     * @description 往综合素质分中填写德育加分
     * **/
    private void setMoralPlus(ComprehensiveQuality comprehensiveQuality){
        ComprehensiveQuality comprehensiveQuality1 = findComprehensiveQuality(comprehensiveQuality);
        assert comprehensiveQuality1 != null;
        comprehensiveQuality1.setMoralPlusNameList(comprehensiveQuality.getMoralPlusNameList());
        comprehensiveQuality1.setMoralPlusScore(comprehensiveQuality.getMoralPlusScore());
        comprehensiveQuality1.setStates(comprehensiveQuality.getStates());
        comPrehensiveQualityRespority.save(comprehensiveQuality1);
    }


    /**
     * @param comprehensiveQuality
     * @description 往综合素质分中填写课外加分
     * **/
    private void setMoralOut(ComprehensiveQuality comprehensiveQuality){
        ComprehensiveQuality comprehensiveQuality1 = findComprehensiveQuality(comprehensiveQuality);
        assert comprehensiveQuality1 != null;
        comprehensiveQuality1.setMoralOutNameList(comprehensiveQuality.getMoralOutNameList());
        comprehensiveQuality1.setMoralOutScore(comprehensiveQuality.getMoralOutScore());
        comprehensiveQuality1.setStates(comprehensiveQuality.getStates());
        comPrehensiveQualityRespority.save(comprehensiveQuality1);
    }
}

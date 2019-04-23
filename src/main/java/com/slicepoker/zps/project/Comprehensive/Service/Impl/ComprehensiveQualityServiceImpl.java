package com.slicepoker.zps.project.Comprehensive.Service.Impl;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import com.slicepoker.zps.project.Comprehensive.Respority.ComPrehensiveQualityRespority;
import com.slicepoker.zps.project.Comprehensive.Service.ComprehensiveQualityService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * @param comprehensiveQuality
     * @param pageable
     * @description 模糊查询综合素质分
     * **/
    @Override
    public Commes findFuzzy(ComprehensiveQuality comprehensiveQuality, Pageable pageable) {
        try {
            Page<ComprehensiveQuality> page = comPrehensiveQualityRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (comprehensiveQuality.getStudentNumber()!=null && !"".equals(comprehensiveQuality.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),comprehensiveQuality.getStudentNumber()));
                }
                if (comprehensiveQuality.getStudentName()!=null && !"".equals(comprehensiveQuality.getStudentName())){
                    list.add(cb.equal(root.get("studentName"),comprehensiveQuality.getStudentName()));
                }
                if (comprehensiveQuality.getMajor()!=null && !"".equals(comprehensiveQuality.getMajor())){
                    list.add(cb.equal(root.get("major"),comprehensiveQuality.getMajor()));
                }
                if (comprehensiveQuality.getGrade()!=null && !"".equals(comprehensiveQuality.getGrade())){
                    list.add(cb.equal(root.get("grade"),comprehensiveQuality.getGrade()));
                }
                if(comprehensiveQuality.getStates()!=null && !"".equals(comprehensiveQuality.getStates())){
                    list.add(cb.equal(root.get("states"),comprehensiveQuality.getStates()));
                }
                if (comprehensiveQuality.getComprehensiveQualityYear()!=null && !"".equals(comprehensiveQuality.getComprehensiveQualityYear())){
                    list.add(cb.equal(root.get("comprehensiveQualityYear"),comprehensiveQuality.getComprehensiveQualityYear()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }


    /**
     * @param studentNumber
     * @description 查找个人综合素质分
     * **/
    @Override
    public Commes findPersonal(Long studentNumber) {
        try {
            List<ComprehensiveQuality> list = comPrehensiveQualityRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("201","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    /**
     * @param id
     * @param states
     * @description 设置状态
     * **/
    @Override
    public Commes setStates(Long id,String states) {
        try {
            ComprehensiveQuality comprehensiveQuality = comPrehensiveQualityRespority.findByIdAndDeletedIsFalse(id);
            if (comprehensiveQuality!=null){
                comprehensiveQuality.setStates(states);
                comPrehensiveQualityRespority.save(comprehensiveQuality);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}

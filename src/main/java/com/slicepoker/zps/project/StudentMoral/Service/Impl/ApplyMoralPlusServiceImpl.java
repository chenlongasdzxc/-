package com.slicepoker.zps.project.StudentMoral.Service.Impl;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import com.slicepoker.zps.project.Comprehensive.Service.ComprehensiveQualityService;
import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOutTotal;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlusTotal;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralOutRespority;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralOutTotalRespority;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralPlusRespority;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralPlusTotalRespority;
import com.slicepoker.zps.project.StudentMoral.Service.ApplyMoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2019/3/17 21:55
 **/
@Service
public class ApplyMoralPlusServiceImpl implements ApplyMoralPlusService {

    @Autowired
    private StudentMoralPlusRespority studentMoralPlusRespority;

    @Autowired
    private StudentMoralOutRespority studentMoralOutRespority;

    @Autowired
    private StudentMoralOutTotalRespority studentMoralOutTotalRespority;

    @Autowired
    private StudentMoralPlusTotalRespority studentMoralPlusTotalRespority;


    @Autowired
    private ComprehensiveQualityService comprehensiveQualityService;

    @Override
    public Commes applyMoral(StudentMoralPlus studentMoralPlus) {
        try {
            return Commes.successMes();
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("","");
        }
    }




    /**
     * @description 申请综合素质德育加分
     * @param studentMoralPlus
     * **/
    @Override
    public Commes updateMoralPlus(StudentMoralPlus studentMoralPlus) {
        try {
            StudentMoralPlus studentMoralPlus1 = studentMoralPlusRespority.findByIdAndStatesAndDeletedIsFalse(studentMoralPlus.getId(),"MP002");
            if (studentMoralPlus1!=null){
                studentMoralPlus1.setComprehensiveQualityStates(studentMoralPlus.getComprehensiveQualityStates());
                studentMoralPlus1.setApplyComprehensiveName(studentMoralPlus.getApplyComprehensiveName());
                studentMoralPlus1.setApplyValue(studentMoralPlus.getApplyValue());
                studentMoralPlus1.setApplyComprehensiveNumber(studentMoralPlus.getApplyComprehensiveNumber());
                if (moralPlusFunction(studentMoralPlus1)){
                    studentMoralPlusRespority.save(studentMoralPlus1);
                    double score = sumMoralPlusTotal(studentMoralPlus1);
                    setStudentMoralPlusTotal(studentMoralPlus1,score);
                    return Commes.successMes();
                }else {
                    return Commes.errorMes("403","已申请该类型");
                }
            }else {
                return Commes.errorMes("402","没有找到该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }

    /**
     * @param studentMoralPlus
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(StudentMoralPlus studentMoralPlus, Pageable pageable) {
        try {
            Page<StudentMoralPlus> page = studentMoralPlusRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (studentMoralPlus.getGrade()!=null && !"".equals(studentMoralPlus.getGrade())){
                    list.add(cb.equal(root.get("grade"),studentMoralPlus.getGrade()));
                }
                if (studentMoralPlus.getStates()!=null && !"".equals(studentMoralPlus.getStates())){
                    list.add(cb.equal(root.get("states"),studentMoralPlus.getStates()));
                }
                if (studentMoralPlus.getStudentClass()!=null && !"".equals(studentMoralPlus.getStudentClass())){
                    list.add(cb.equal(root.get("studentClass"),studentMoralPlus.getStudentClass()));
                }
                if (studentMoralPlus.getStudentNumber()!=null && !"".equals(studentMoralPlus.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),studentMoralPlus.getStudentNumber()));
                }
                if (studentMoralPlus.getMoralPlusType()!=null && !"".equals(studentMoralPlus.getMoralPlusType())){
                    list.add(cb.equal(root.get("moralPlusType"),studentMoralPlus.getMoralPlusType()));
                }
                if (studentMoralPlus.getYear()!=null && !"".equals(studentMoralPlus.getYear())){
                    list.add(cb.equal(root.get("year"),studentMoralPlus.getYear()));
                }
                if (studentMoralPlus.getComprehensiveQualityStates()!=null && !"".equals(studentMoralPlus.getComprehensiveQualityStates())){
                    list.add(cb.equal(root.get("comprehensiveQualityStates"),studentMoralPlus.getComprehensiveQualityStates()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }

    /**
     * @param studentMoralPlus
     * @description 判断德育加分
     * **/
    private boolean moralPlusFunction(StudentMoralPlus studentMoralPlus){
        try {
            List<StudentMoralPlus> list = studentMoralPlusRespority.findStudentMoralPlus(
                    studentMoralPlus.getStudentNumber(),
                    studentMoralPlus.getMoralPlusType(),
                    studentMoralPlus.getMoralPlusName(),
                    studentMoralPlus.getYear(),
                    studentMoralPlus.getComprehensiveQualityStates());
            if (list.size()==0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param id
     * @description 取消综合素质德育加分申请
     * **/
    @Override
    public Commes cancelApply(Long id) {
        try {
            StudentMoralPlus studentMoralPlus = studentMoralPlusRespority.findByIdAndDeletedIsFalse(id);
            if (studentMoralPlus!=null){
                studentMoralPlus.setComprehensiveQualityStates("");
                studentMoralPlusRespority.save(studentMoralPlus);
                return Commes.successMes();
            }else {
                return Commes.errorMes("402","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }

    /**
     * @param studentMoralOut
     * @description 更新申请综合素质课外加分
     * **/
    @Override
    public Commes updateMoralOut(StudentMoralOut studentMoralOut) {
        try {
            StudentMoralOut studentMoralOut1 = studentMoralOutRespority.findByIdAndDeletedIsFalse(studentMoralOut.getId());
            if (studentMoralOut1!=null){
                studentMoralOut1.setComprehensiveQualityStates(studentMoralOut.getComprehensiveQualityStates());
                studentMoralOut1.setApplyValue(studentMoralOut.getApplyValue());
                studentMoralOut1.setApplyComprehensiveNumber(studentMoralOut.getApplyComprehensiveNumber());
                studentMoralOut1.setApplyComprehensiveName(studentMoralOut.getApplyComprehensiveName());
                studentMoralOutRespority.save(studentMoralOut1);
                double score = sumMoralOutTotal(studentMoralOut1);
                setMoralOutTotal(studentMoralOut1,score);
                return Commes.successMes();
            }else {
                return Commes.errorMes("402","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }

    /**
     * @param pageable
     * @param studentMoralOut
     * @description 模糊查询申请综合素质课外加分
     * **/
    @Override
    public Commes findFuzzyMoralOut(StudentMoralOut studentMoralOut, Pageable pageable) {
        try {
            Page<StudentMoralOut> page = studentMoralOutRespority.findAll(((root, query, cb) ->{
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("deleted"),false),
                                cb.equal(root.get("states"),"MO002"),
                                cb.equal(root.get("comprehensiveQualityStates"),studentMoralOut.getComprehensiveQualityStates()))
                );
                if (studentMoralOut.getStudentClass()!=null && !"".equals(studentMoralOut.getStudentClass())){
                    list.add(cb.equal(root.get("studentClass"),studentMoralOut.getStudentClass()));
                }
                if (studentMoralOut.getGrade()!=null && !"".equals(studentMoralOut.getGrade())){
                    list.add(cb.equal(root.get("grade"),studentMoralOut.getGrade()));
                }
                if (studentMoralOut.getMajor()!=null && !"".equals(studentMoralOut.getMajor())){
                    list.add(cb.equal(root.get("major"),studentMoralOut.getMajor()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }



    private double sumMoralOutTotal(StudentMoralOut studentMoralOut){
        Double score = studentMoralOutRespority.sunMoralOutScore(studentMoralOut.getStudentNumber(),studentMoralOut.getYear());
        if (score!=null){
            return score;
        }else {
            return 0;
        }

    }


    /**
     * @param score
     * @param studentMoralOut
     * @description 设置已审核综合素质课外加分总分，并将数据录入综合素质
     * **/
    private void setMoralOutTotal(StudentMoralOut studentMoralOut,double score){
        StudentMoralOutTotal studentMoralOutTotal = studentMoralOutTotalRespority.findByStudentNumberAndMoralOutYearAndDeletedIsFalse(studentMoralOut.getStudentNumber(),studentMoralOut.getYear());
        if (studentMoralOutTotal==null){
            StudentMoralOutTotal studentMoralOutTotal1 = new StudentMoralOutTotal();
            studentMoralOutTotal1.setStudentNumber(studentMoralOut.getStudentNumber());
            studentMoralOutTotal1.setStudentName(studentMoralOut.getStudentName());
            studentMoralOutTotal1.setStudentClass(studentMoralOut.getStudentClass());
            studentMoralOutTotal1.setMajor(studentMoralOut.getMajor());
            studentMoralOutTotal1.setGrade(studentMoralOut.getGrade());
            studentMoralOutTotal1.setMoralOutYear(studentMoralOut.getYear());
            studentMoralOutTotal1.setMoralOutTotal(score);
            studentMoralOutTotal1.setMoralOutNameList(setMoralOutNameList(studentMoralOut));
            studentMoralOutTotalRespority.save(studentMoralOutTotal1);
            ComprehensiveQuality comprehensiveQuality = setComprehensiveQualityMoralOut(studentMoralOutTotal1);
            comprehensiveQualityService.update(comprehensiveQuality);
        }else {
            studentMoralOutTotal.setMoralOutTotal(score);
            studentMoralOutTotal.setMoralOutNameList(setMoralOutNameList(studentMoralOut));
            studentMoralOutTotalRespority.save(studentMoralOutTotal);
            ComprehensiveQuality comprehensiveQuality = setComprehensiveQualityMoralOut(studentMoralOutTotal);
            comprehensiveQualityService.update(comprehensiveQuality);
        }
    }

    private double sumMoralPlusTotal(StudentMoralPlus studentMoralPlus){
        Double score = studentMoralPlusRespority.sumMoralPlusScore(studentMoralPlus.getStudentNumber(),studentMoralPlus.getYear());
        if (score!=null){
            return score;
        }else {
            return 0;
        }
    }

    /**
     * @param studentMoralPlus
     * @param score
     * @description 设置已审核综合素质德育加分总分，并将数据录入综合素质
     * **/
    private void setStudentMoralPlusTotal(StudentMoralPlus studentMoralPlus,double score){
        StudentMoralPlusTotal studentMoralPlusTotal = studentMoralPlusTotalRespority.findByStudentNumberAndMoralPlusYearAndDeletedIsFalse(studentMoralPlus.getStudentNumber(),studentMoralPlus.getYear());
        if (studentMoralPlusTotal==null){
            StudentMoralPlusTotal studentMoralPlusTotal1 = new StudentMoralPlusTotal();
            studentMoralPlusTotal1.setStudentNumber(studentMoralPlus.getStudentNumber());
            studentMoralPlusTotal1.setStudentName(studentMoralPlus.getStudentName());
            studentMoralPlusTotal1.setStudentClass(studentMoralPlus.getStudentClass());
            studentMoralPlusTotal1.setMajor(studentMoralPlus.getMajor());
            studentMoralPlusTotal1.setGrade(studentMoralPlus.getGrade());
            studentMoralPlusTotal1.setMoralPlusYear(studentMoralPlus.getYear());
            studentMoralPlusTotal1.setMoralPlusNameList(setMoralPlusNameList(studentMoralPlus));
            studentMoralPlusTotal1.setMoralPlusTotal(score);
            studentMoralPlusTotalRespority.save(studentMoralPlusTotal1);
            ComprehensiveQuality comprehensiveQuality = setComprehensiveQualityMoralPlus(studentMoralPlusTotal1);
            comprehensiveQualityService.update(comprehensiveQuality);
        }else {
            studentMoralPlusTotal.setMoralPlusTotal(score);
            studentMoralPlusTotal.setMoralPlusNameList(setMoralPlusNameList(studentMoralPlus));
            studentMoralPlusTotalRespority.save(studentMoralPlusTotal);
            ComprehensiveQuality comprehensiveQuality = setComprehensiveQualityMoralPlus(studentMoralPlusTotal);
            comprehensiveQualityService.update(comprehensiveQuality);
        }
    }

    /**
     * @param studentMoralOut
     * @description 设置课外加分名称list
     * **/
    private String setMoralOutNameList(StudentMoralOut studentMoralOut){
       String moralOutNameList = studentMoralOutRespority.concatMoralOutName(studentMoralOut.getStudentNumber(),studentMoralOut.getYear());
       if (moralOutNameList!=null && !"".equals(moralOutNameList)){
           return moralOutNameList;
       }else {
           return "";
       }
    }

    /**
     * @param studentMoralPlus
     * @description 设置德育加分名称list
     * **/
    private String setMoralPlusNameList(StudentMoralPlus studentMoralPlus){
        String moralPlusNameList = studentMoralPlusRespority.concatMoralOutName(studentMoralPlus.getStudentNumber(),studentMoralPlus.getYear());
        if (moralPlusNameList!=null && !"".equals(moralPlusNameList)){
            return moralPlusNameList;
        }else {
            return "";
        }
    }

    /**
     * @param studentMoralOutTotal
     * @return comprehensiveQuality
     * @description 设置综合素质课外加分
     * **/
    private ComprehensiveQuality setComprehensiveQualityMoralOut(StudentMoralOutTotal studentMoralOutTotal){
        ComprehensiveQuality comprehensiveQuality1 = new ComprehensiveQuality();
        comprehensiveQuality1.setStudentNumber(studentMoralOutTotal.getStudentNumber());
        comprehensiveQuality1.setStudentClass(studentMoralOutTotal.getStudentClass());
        comprehensiveQuality1.setStudentName(studentMoralOutTotal.getStudentName());
        comprehensiveQuality1.setGrade(studentMoralOutTotal.getGrade());
        comprehensiveQuality1.setMajor(studentMoralOutTotal.getMajor());
        comprehensiveQuality1.setMoralOutNameList(studentMoralOutTotal.getMoralOutNameList());  //课外加分名称list
        comprehensiveQuality1.setMoralOutScore(studentMoralOutTotal.getMoralOutTotal());   //课外加分分数
        comprehensiveQuality1.setStates("CM001");  //未查看
        comprehensiveQuality1.setComprehensiveQualityYear(studentMoralOutTotal.getMoralOutYear());
        return comprehensiveQuality1;
    }


    /**
     * @param studentMoralPlusTotal
     * @return ComprehensiveQuality
     * @description 设置综合素质德育加分
     * **/
    private ComprehensiveQuality setComprehensiveQualityMoralPlus(StudentMoralPlusTotal studentMoralPlusTotal){
        ComprehensiveQuality comprehensiveQuality1 = new ComprehensiveQuality();
        comprehensiveQuality1.setStudentNumber(studentMoralPlusTotal.getStudentNumber());
        comprehensiveQuality1.setStudentClass(studentMoralPlusTotal.getStudentClass());
        comprehensiveQuality1.setStudentName(studentMoralPlusTotal.getStudentName());
        comprehensiveQuality1.setGrade(studentMoralPlusTotal.getGrade());
        comprehensiveQuality1.setMajor(studentMoralPlusTotal.getMajor());
        comprehensiveQuality1.setMoralPlusNameList(studentMoralPlusTotal.getMoralPlusNameList());  //德育加分名称list
        comprehensiveQuality1.setMoralPlusScore(studentMoralPlusTotal.getMoralPlusTotal());   //德育加分分数
        comprehensiveQuality1.setStates("CM001");  //未查看
        comprehensiveQuality1.setComprehensiveQualityYear(studentMoralPlusTotal.getMoralPlusYear());  //年度
        return comprehensiveQuality1;
    }

}

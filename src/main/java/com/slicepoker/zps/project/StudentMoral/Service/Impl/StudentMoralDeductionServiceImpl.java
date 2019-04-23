package com.slicepoker.zps.project.StudentMoral.Service.Impl;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import com.slicepoker.zps.project.Comprehensive.Service.ComprehensiveQualityService;
import com.slicepoker.zps.project.Moral.Pojo.MoralDeduction;
import com.slicepoker.zps.project.Moral.Respority.MoralDeductionRespority;
import com.slicepoker.zps.project.Moral.Service.MoralDeductionService;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeduction;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeductionTotal;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralDeductionRespority;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralDeductionTotalRespority;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralDeductionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2019/4/15 14:37
 **/
@Service
public class StudentMoralDeductionServiceImpl implements StudentMoralDeductionService {

    @Autowired
    private StudentMoralDeductionRespority studentMoralDeductionRespority;

    @Autowired
    private MoralDeductionService moralDeductionService;

    @Autowired
    private StudentMoralDeductionTotalRespority studentMoralDeductionTotalRespority;

    @Autowired
    private ComprehensiveQualityService comprehensiveQualityService;


    /**
     * @param id
     * @description 删除
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            StudentMoralDeduction studentMoralDeduction = studentMoralDeductionRespority.findByIdAndDeletedIsFalse(id);
            if (studentMoralDeduction!=null){
                studentMoralDeduction.setDeleted(true);
                studentMoralDeductionRespority.save(studentMoralDeduction);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","没有找到该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }


    /**
     * @param studentMoralDeduction
     * @description 新增
     * **/
    @Override
    public Commes add(StudentMoralDeduction studentMoralDeduction) {
        try {
            MoralDeduction moralDeduction = moralDeductionService.findByMoralDeductionType(studentMoralDeduction.getMoralDeductionType());
            if (moralDeduction!=null){
                studentMoralDeduction.setMoralDeductionScore(moralDeduction.getMoralDeductionScore()*studentMoralDeduction.getMoralDeductionNumber());
                studentMoralDeduction.setStates("MD001");
                studentMoralDeductionRespority.save(studentMoralDeduction);
                double score = sumMoralDeductionScore(studentMoralDeduction);
                setStudentMoralDeductionTotal(studentMoralDeduction,score);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","查询德育减分失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    /**
     * @param pageable
     * @param studentMoralDeduction
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(StudentMoralDeduction studentMoralDeduction, Pageable pageable) {
       try {
           Page<StudentMoralDeduction> page = studentMoralDeductionRespority.findAll(((root, query, cb) -> {
               List<Predicate> list = new ArrayList<>();
               list.add(cb.equal(root.get("deleted"),false));
               if (studentMoralDeduction.getGrade()!=null && !"".equals(studentMoralDeduction.getGrade())){
                   list.add(cb.equal(root.get("grade"),studentMoralDeduction.getGrade()));
               }
               if (studentMoralDeduction.getMajor()!=null && !"".equals(studentMoralDeduction.getMajor())){
                   list.add(cb.equal(root.get("major"),studentMoralDeduction.getMajor()));
               }
               if (studentMoralDeduction.getApplyPersonName()!=null && !"".equals(studentMoralDeduction.getApplyPersonName())){
                   list.add(cb.equal(root.get("applyPersonName"),studentMoralDeduction.getApplyPersonName()));
               }
               if(studentMoralDeduction.getStudentClass()!=null && !"".equals(studentMoralDeduction.getStudentClass())){
                   list.add( cb.equal(root.get("studentClass"),studentMoralDeduction.getStudentClass()));
               }
               if (studentMoralDeduction.getMoralDeductionYear()!=null && !"".equals(studentMoralDeduction.getMoralDeductionYear())){
                   list.add(cb.equal(root.get("moralDeductionYear"),studentMoralDeduction.getMoralDeductionYear()));
               }
               if (studentMoralDeduction.getStates()!=null && !"".equals(studentMoralDeduction.getStates())){
                   list.add(cb.equal(root.get("states"),studentMoralDeduction.getStates()));
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
     * @param pageable
     * @param studentMoralDeduction
     * @description 获取个人德育减分数据
     * ***/
    @Override
    public Commes findPersonalData(StudentMoralDeduction studentMoralDeduction, Pageable pageable) {
        try {
            Page<StudentMoralDeduction> page = studentMoralDeductionRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (studentMoralDeduction.getMajor()!=null && !"".equals(studentMoralDeduction.getMajor())){
                    list.add(cb.equal(root.get("major"),studentMoralDeduction.getMajor()));
                }
                if (studentMoralDeduction.getStudentClass()!=null && !"".equals(studentMoralDeduction.getStudentClass())){
                    list.add(cb.equal(root.get("studentClass"),studentMoralDeduction.getStudentClass()));
                }
                if (studentMoralDeduction.getGrade()!=null && !"".equals(studentMoralDeduction.getGrade())){
                    list.add(cb.equal(root.get("grade"),studentMoralDeduction.getGrade()));
                }
                if (studentMoralDeduction.getStudentName()!=null && !"".equals(studentMoralDeduction.getStudentName())){
                    list.add(cb.equal(root.get("studentName"),studentMoralDeduction.getStudentName()));
                }
                if (studentMoralDeduction.getStates()!=null && !"".equals(studentMoralDeduction.getStates())){
                    list.add(cb.equal(root.get("states"),studentMoralDeduction.getStates()));
                }
                if (studentMoralDeduction.getStudentNumber()!=null && !"".equals(studentMoralDeduction.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),studentMoralDeduction.getStudentNumber()));
                }
                if(studentMoralDeduction.getMoralDeductionYear()!=null && !"".equals(studentMoralDeduction.getMoralDeductionYear())){
                    list.add(cb.equal(root.get("moralDeductionYear"),studentMoralDeduction.getMoralDeductionYear()));
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
     * @param id
     * @param states
     * @description 更新德育减分状态
     * **/
    @Override
    public Commes update(Long id, String states) {
        try {
            StudentMoralDeduction studentMoralDeduction = studentMoralDeductionRespority.findByIdAndDeletedIsFalse(id);
            if (studentMoralDeduction!=null){
                studentMoralDeduction.setStates(states);
                studentMoralDeductionRespority.save(studentMoralDeduction);
                if (Objects.equals(studentMoralDeduction.getStates(),"MD002")){
                    double score = sumMoralDeductionScore(studentMoralDeduction);
                    setStudentMoralDeductionTotal(studentMoralDeduction,score);
                }
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","没有找到该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    private double sumMoralDeductionScore(StudentMoralDeduction studentMoralDeduction){
        Double score = studentMoralDeductionRespority.sumMoralDeductionScore(studentMoralDeduction.getStudentNumber(),studentMoralDeduction.getMoralDeductionYear());
        if (score!=null && !"".equals(score)){
            return score;
        }else {
            return 0;
        }
    }

    private void setStudentMoralDeductionTotal(StudentMoralDeduction studentMoralDeduction,double score){
        StudentMoralDeductionTotal studentMoralDeductionTotal = studentMoralDeductionTotalRespority.findByStudentNumberAndYearAndDeletedIsFalse(studentMoralDeduction.getStudentNumber(),studentMoralDeduction.getMoralDeductionYear());
        if (studentMoralDeductionTotal==null){
            StudentMoralDeductionTotal studentMoralDeductionTotal1 = new StudentMoralDeductionTotal();
            studentMoralDeductionTotal1.setYear(studentMoralDeduction.getMoralDeductionYear());
            studentMoralDeductionTotal1.setGrade(studentMoralDeduction.getGrade());
            studentMoralDeductionTotal1.setMajor(studentMoralDeduction.getMajor());
            studentMoralDeductionTotal1.setStudentClass(studentMoralDeduction.getStudentClass());
            studentMoralDeductionTotal1.setStudentName(studentMoralDeduction.getStudentName());
            studentMoralDeductionTotal1.setStudentNumber(studentMoralDeduction.getStudentNumber());
            studentMoralDeductionTotal1.setStudentMoralDeductionScoreTotal(score);
            studentMoralDeductionTotal1.setMoralDeductionNameList(moralDeductionNameList(studentMoralDeduction));
            studentMoralDeductionTotalRespority.save(studentMoralDeductionTotal1);
            ComprehensiveQuality comprehensiveQuality = setComprehensiveQualityMoralDeduction(studentMoralDeductionTotal1);
            comprehensiveQualityService.update(comprehensiveQuality);
        }else {
            studentMoralDeductionTotal.setStudentMoralDeductionScoreTotal(score);
            studentMoralDeductionTotal.setMoralDeductionNameList(moralDeductionNameList(studentMoralDeduction));
            studentMoralDeductionTotalRespority.save(studentMoralDeductionTotal);
            ComprehensiveQuality comprehensiveQuality = setComprehensiveQualityMoralDeduction(studentMoralDeductionTotal);
            comprehensiveQualityService.update(comprehensiveQuality);
        }
    }

    private String moralDeductionNameList(StudentMoralDeduction studentMoralDeduction){
        String moralDeductionNameList = studentMoralDeductionRespority.moralDeductionName(studentMoralDeduction.getStudentNumber(),studentMoralDeduction.getMoralDeductionYear());
        if (moralDeductionNameList!=null && !"".equals(moralDeductionNameList)){
            return moralDeductionNameList;
        }else {
            return null;
        }
    }

    private ComprehensiveQuality setComprehensiveQualityMoralDeduction(StudentMoralDeductionTotal studentMoralDeductionTotal){
        ComprehensiveQuality comprehensiveQuality1 = new ComprehensiveQuality();
        comprehensiveQuality1.setStudentNumber(studentMoralDeductionTotal.getStudentNumber());
        comprehensiveQuality1.setStudentClass(studentMoralDeductionTotal.getStudentClass());
        comprehensiveQuality1.setStudentName(studentMoralDeductionTotal.getStudentName());
        comprehensiveQuality1.setGrade(studentMoralDeductionTotal.getGrade());
        comprehensiveQuality1.setMajor(studentMoralDeductionTotal.getMajor());
        comprehensiveQuality1.setMoralDeductionNameList(studentMoralDeductionTotal.getMoralDeductionNameList());  //课外加分名称list
        comprehensiveQuality1.setMoralDeductionScore(studentMoralDeductionTotal.getStudentMoralDeductionScoreTotal());   //课外加分分数
        comprehensiveQuality1.setStates("CM001");  //未查看
        comprehensiveQuality1.setComprehensiveQualityYear(studentMoralDeductionTotal.getYear());
        return comprehensiveQuality1;
    }

    /**
     * @param pageable
     * @param studentMoralDeductionTotal
     * @description
     * **/
    @Override
    public Commes findFuzzyTotal(StudentMoralDeductionTotal studentMoralDeductionTotal, Pageable pageable) {
        try {
            Page<StudentMoralDeductionTotal> page = studentMoralDeductionTotalRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (studentMoralDeductionTotal.getStudentNumber()!=null && !"".equals(studentMoralDeductionTotal.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),studentMoralDeductionTotal.getStudentNumber()));
                }
                if (studentMoralDeductionTotal.getGrade()!=null && !"".equals(studentMoralDeductionTotal.getGrade())){
                    list.add(cb.equal(root.get("grade"),studentMoralDeductionTotal.getGrade()));
                }
                if (studentMoralDeductionTotal.getMajor()!=null && !"".equals(studentMoralDeductionTotal.getMajor())){
                    list.add(cb.equal(root.get("major"),studentMoralDeductionTotal.getMajor()));
                }
                if (studentMoralDeductionTotal.getStudentClass()!=null && !"".equals(studentMoralDeductionTotal.getStudentClass())){
                    list.add(cb.equal(root.get("studentClass"),studentMoralDeductionTotal.getStudentClass()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}

package com.slicepoker.zps.project.StudentMoral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralDeduction;
import com.slicepoker.zps.project.Moral.Respority.MoralDeductionRespority;
import com.slicepoker.zps.project.Moral.Service.MoralDeductionService;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralDeduction;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralDeductionRespority;
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
               list.add(
                               cb.and(
                                    cb.equal(root.get("deleted"),false),
                                    cb.equal(root.get("major"),studentMoralDeduction.getMajor())
                               )
               );
               if (studentMoralDeduction.getApplyPersonNumber()!=null && !"".equals(studentMoralDeduction.getApplyPersonNumber())){
                   list.add(cb.equal(root.get("applyPersonNumber"),studentMoralDeduction.getApplyPersonNumber()));
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
                list.add(cb.and(
                        cb.equal(root.get("deleted"),false),
                        cb.equal(root.get("major"),studentMoralDeduction.getMajor()),
                        cb.equal(root.get("studentClass"),studentMoralDeduction.getStudentClass())
                ));
                if (studentMoralDeduction.getStudentName()!=null && !"".equals(studentMoralDeduction.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),studentMoralDeduction.getStudentNumber()));
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
}

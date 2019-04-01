package com.slicepoker.zps.project.StudentMoral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralOutRespority;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralPlusRespority;
import com.slicepoker.zps.project.StudentMoral.Service.ApplyMoralPlusService;
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
 * @date 2019/3/17 21:55
 **/
@Service
public class ApplyMoralPlusServiceImpl implements ApplyMoralPlusService {

    @Autowired
    private StudentMoralPlusRespority studentMoralPlusRespority;

    @Autowired
    private StudentMoralOutRespority studentMoralOutRespority;

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
    public Commes update(StudentMoralPlus studentMoralPlus) {
        try {
            StudentMoralPlus studentMoralPlus1 = studentMoralPlusRespority.findByIdAndStatesAndDeletedIsFalse(studentMoralPlus.getId(),"MP002");
            if (studentMoralPlus1!=null){
                studentMoralPlus1.setComprehensiveQualityStates(studentMoralPlus.getComprehensiveQualityStates());
                studentMoralPlus1.setApplyComprehensiveName(studentMoralPlus.getApplyComprehensiveName());
                studentMoralPlus1.setApplyComprehensiveNumber(studentMoralPlus.getApplyComprehensiveNumber());
                if (moralPlusFunction(studentMoralPlus1)){
                    studentMoralPlusRespority.save(studentMoralPlus1);
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
                list.add(
                        cb.and(
                                cb.and(
                                        cb.equal(root.get("grade"),studentMoralPlus.getGrade()),
                                        cb.equal(root.get("deleted"),false),
                                        cb.equal(root.get("states"),studentMoralPlus.getStates()),
                                        cb.equal(root.get("studentClass"),studentMoralPlus.getStudentClass())
                                ),
                                cb.or(
                                  cb.equal(root.get("studentNumber"),studentMoralPlus.getStudentNumber()),
                                  cb.equal(root.get("comprehensiveQualityStates"),studentMoralPlus.getComprehensiveQualityStates())
                                )
                        )
                );
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
     * @description 判断
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
     * @description 申请综合素质课外加分
     * **/
    @Override
    public Commes updateMoralOut(StudentMoralOut studentMoralOut) {
        try {
            StudentMoralOut studentMoralOut1 = studentMoralOutRespority.findByIdAndDeletedIsFalse(studentMoralOut.getId());
            if (studentMoralOut1!=null){
                studentMoralOut1.setComprehensiveQualityStates(studentMoralOut.getComprehensiveQualityStates());
                studentMoralOutRespority.saveAndFlush(studentMoralOut1);
                return Commes.successMes();
            }else {
                return Commes.errorMes("402","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }


    @Override
    public Commes findFuzzyMoralOut(StudentMoralOut studentMoralOut, Pageable pageable) {
        try {
            Page<StudentMoralOut> page = studentMoralOutRespority.findAll(((root, query, cb) ->{
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.or(
                                cb.and(
                                        cb.equal(root.get("deleted"),false),
                                        cb.equal(root.get("states"),"MO002"),
                                        cb.equal(root.get("comprehensiveQualityStates"),studentMoralOut.getComprehensiveQualityStates())
                                ),
                                cb.or(
                                  cb.equal(root.get("studentClass"),studentMoralOut.getStudentClass())
                                )
                        )
                );
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }
}

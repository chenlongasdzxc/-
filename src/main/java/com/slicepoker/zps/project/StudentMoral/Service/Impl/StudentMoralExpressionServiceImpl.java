package com.slicepoker.zps.project.StudentMoral.Service.Impl;

import com.slicepoker.zps.project.Moral.Respority.MoralExpressionRespority;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpression;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralExpressionTotal;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralExpressionRespority;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralExpressionTotalRespority;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralExpressionService;
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
 * @date 2019/3/22 16:47
 **/
@Service
public class StudentMoralExpressionServiceImpl implements StudentMoralExpressionService {

    @Autowired
    private StudentMoralExpressionRespority studentMoralExpressionRespority;

    @Autowired
    private StudentMoralExpressionTotalRespority studentMoralExpressionTotalRespority;

    @Autowired
    private MoralExpressionRespority moralExpressionRespority;

    /**
     * @param studentMoralExpression
     * @description 查找个人德育表现
     * **/
    @Override
    public Commes findPersonalMoralExpression(StudentMoralExpression studentMoralExpression) {
        try {
            List<StudentMoralExpression> list = studentMoralExpressionRespority.findAll(((root, query, cb) -> {
                List<Predicate> list1 = new ArrayList<>();
                list1.add(
                        cb.and(
                                cb.and(
                                        cb.equal(root.get("deleted"),false),
                                        cb.equal(root.get("grade"),studentMoralExpression.getGrade()),
                                        cb.equal(root.get("studentClass"),studentMoralExpression.getStudentClass())
                                ),
                                cb.or(
                                        cb.equal(root.get("studentNumber"),studentMoralExpression.getStudentNumber()),
                                        cb.equal(root.get("moralExpressionYear"),studentMoralExpression.getMoralExpressionYear()),
                                        cb.equal(root.get("moralExpressionName"),studentMoralExpression.getMoralExpressionName())
                                )
                        )
                );
                if (studentMoralExpression.getStates()!=null && !"".equals(studentMoralExpression.getStates())){
                    list1.add(
                        cb.equal(root.get("states"),studentMoralExpression.getStates())
                    );
                }
                return cb.and(list1.toArray(new Predicate[list1.size()]));
            }));
         return Commes.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }


    /**
     * @param studentMoralExpression
     * @description 保存新增数据
     * **/
    @Override
    public Commes update(StudentMoralExpression studentMoralExpression) {
        try {
            StudentMoralExpression studentMoralExpression1 = studentMoralExpressionRespority.findByStudentNumberAndMoralExpressionNameAndMoralExpressionYearAndDeletedIsFalse(
                    studentMoralExpression.getStudentNumber(),
                    studentMoralExpression.getMoralExpressionName(),
                    studentMoralExpression.getMoralExpressionYear()
            );
            if (studentMoralExpression1==null){
                studentMoralExpression.setStates("ME001");
                studentMoralExpressionRespority.save(studentMoralExpression);
                return Commes.successMes();
            }else {
                return Commes.errorMes("402","已存在该条数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","保存失败");
        }
    }

    /**
     * @param studentMoralExpression
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(StudentMoralExpression studentMoralExpression, Pageable pageable) {
        try {
            Page<StudentMoralExpression> page = studentMoralExpressionRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.and(
                                        cb.equal(root.get("deleted"),false),
                                        cb.equal(root.get("grade"),studentMoralExpression.getGrade())
                                ),
                                cb.or(
                                        cb.equal(root.get("studentClass"),studentMoralExpression.getStudentClass()),
                                        cb.equal(root.get("studentName"),studentMoralExpression.getStudentName()),
                                        cb.equal(root.get("moralExpressionYear"),studentMoralExpression.getMoralExpressionYear())
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
     * @param studentMoralExpression
     * @description 对德育表现无异议或有异议
     * **/
    @Override
    public Commes checkMoralExpression(StudentMoralExpression studentMoralExpression) {
        try {
            StudentMoralExpression studentMoralExpression1 = studentMoralExpressionRespority.findByIdAndDeletedIsFalse(studentMoralExpression.getId());
            if (studentMoralExpression1!=null){
                studentMoralExpression1.setStates(studentMoralExpression.getStates());
                studentMoralExpressionRespority.saveAndFlush(studentMoralExpression1);
                if (Objects.equals(studentMoralExpression.getStates(),"ME002")){
                    double score = moralExpressionTotalScore(studentMoralExpression1.getStudentNumber(),studentMoralExpression1.getMoralExpressionYear());
                    setStudentMoralExpressionTotal(studentMoralExpression1,score);
                }
                return Commes.successMes();
            }else {
                return Commes.errorMes("402","没有该条数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","保存失败");
        }
    }


    private double moralExpressionTotalScore(Long studentNumber,String moralExpressionYear){
        double score = studentMoralExpressionRespority.moralExpressionScore(studentNumber,moralExpressionYear);
        double average = score/moralExpressionRespority.moralExpressionNameCount();
        return average;
    }


    private void setStudentMoralExpressionTotal(StudentMoralExpression studentMoralExpression,double score){
        StudentMoralExpressionTotal studentMoralExpressionTotal = studentMoralExpressionTotalRespority.findByStudentNumberAndDeletedIsFalse(studentMoralExpression.getStudentNumber());
        if (studentMoralExpressionTotal!=null){
            studentMoralExpressionTotal.setMoralExpressionTotalScore(score);
            studentMoralExpressionTotalRespority.save(studentMoralExpressionTotal);
        }else {
            StudentMoralExpressionTotal studentMoralExpressionTotal1 = new StudentMoralExpressionTotal();
            studentMoralExpressionTotal1.setStudentName(studentMoralExpression.getStudentName());
            studentMoralExpressionTotal1.setStudentNumber(studentMoralExpression.getStudentNumber());
            studentMoralExpressionTotal1.setStudentClass(studentMoralExpression.getStudentClass());
            studentMoralExpressionTotal1.setGrade(studentMoralExpression.getGrade());
            studentMoralExpressionTotal1.setMajor(studentMoralExpression.getMajor());
            studentMoralExpressionTotal1.setMoralExpressionYear(studentMoralExpression.getMoralExpressionYear());
            studentMoralExpressionTotal1.setMoralExpressionTotalScore(score);
            studentMoralExpressionTotalRespority.saveAndFlush(studentMoralExpressionTotal1);
        }
    }

    @Override
    public Commes findMoralExpressionTotal(StudentMoralExpressionTotal studentMoralExpressionTotal,Pageable pageable) {
        try {
            Page<StudentMoralExpressionTotal> page = studentMoralExpressionTotalRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.or(
                                cb.and(
                                        cb.equal(root.get("deleted"),false),
                                        cb.equal(root.get("grade"),studentMoralExpressionTotal.getGrade())
                                ),
                                cb.or(
                                        cb.equal(root.get("studentClass"),studentMoralExpressionTotal.getStudentClass()),
                                        cb.equal(root.get("major"),studentMoralExpressionTotal.getGrade())
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

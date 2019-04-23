package com.slicepoker.zps.project.StudentMoral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import com.slicepoker.zps.project.Moral.Respority.MoralOutRespority;
import com.slicepoker.zps.project.Student.Pojo.StudentMoral;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOut;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralOutTotal;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralOutRespority;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralOutTotalRespority;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralOutService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2019/3/14 8:52
 **/
@Service
public class StudentMoralOutServiceImpl implements StudentMoralOutService {

    @Autowired
    private StudentMoralOutRespority studentMoralOutRespority;

    @Autowired
    private MoralOutRespority moralOutRespority;

    @Autowired
    private StudentMoralOutTotalRespority studentMoralOutTotalRespority;

    /**
     * @param studentMoralOut
     * @description 新增课外加分
     * **/
    @Override
    public Commes add(StudentMoralOut studentMoralOut) {
        try {
            studentMoralOut.setMoralOutScore(findMoralOutScore(studentMoralOut.getMoralOutName(),studentMoralOut.getMoralOutType()));
            studentMoralOut.setStates("MO001");
            studentMoralOut.setUpdateDate(new Date());
            return Commes.success(studentMoralOutRespority.save(studentMoralOut));
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","新增失败");
        }
    }

    /**
     * @param studentMoralOut
     * @description 审核
     * **/
    @Override
    public Commes setStates(StudentMoralOut studentMoralOut) {
        try {
            StudentMoralOut studentMoralOut1 = studentMoralOutRespority.findByIdAndDeletedIsFalse(studentMoralOut.getId());
            if (studentMoralOut1!=null){
                studentMoralOut1.setStates(studentMoralOut.getStates());
                studentMoralOut1.setValue(studentMoralOut.getValue());
                studentMoralOut1.setApplyPersonName(studentMoralOut.getApplyPersonName());
                studentMoralOut1.setApplyPersonNumber(studentMoralOut.getApplyPersonNumber());
                return Commes.success(studentMoralOutRespority.save(studentMoralOut1));
            }else {
                return Commes.errorMes("402","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","审核失败");
        }
    }

    /**
     * @param id
     * @description 删除
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            StudentMoralOut studentMoralOut = studentMoralOutRespority.findByIdAndDeletedIsFalse(id);
            if (studentMoralOut!=null){
                studentMoralOut.setDeleted(true);
                return Commes.success(studentMoralOutRespority.save(studentMoralOut));
            }else {
                return Commes.errorMes("402","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","删除失败");
        }
    }

    private double findMoralOutScore(String moralOutName, String moralOutType){
        MoralOut moralOut = moralOutRespority.findByMoralOutNameAndMoralOutTypeAndDeletedIsFalse(moralOutName, moralOutType);
        double score = moralOut.getMoralOutScore();
        return score;
    }

    /**
     * @param studentMoralOut
     * @param pageable
     * @description 查找个人德育加分
     * **/
    @Override
    public Commes findPersonalMoralOut(StudentMoralOut studentMoralOut, Pageable pageable) {
        try {
            Page<StudentMoralOut> page = studentMoralOutRespority.findAll(((root, query, cb) ->{
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (studentMoralOut.getStudentNumber()!=null && !"".equals(studentMoralOut.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),studentMoralOut.getStudentNumber()));
                }
                if(studentMoralOut.getMoralOutType()!=null && !"".equals(studentMoralOut.getMoralOutType())){
                    list.add(cb.equal(root.get("moralOutType"),studentMoralOut.getMoralOutType()));
                }
                if (studentMoralOut.getYear()!=null && !"".equals(studentMoralOut.getYear())){
                    list.add(cb.equal(root.get("year"),studentMoralOut.getYear()));
                }
                if (studentMoralOut.getStates()!=null && !"".equals(studentMoralOut.getStates())){
                    list.add(cb.equal(root.get("states"),studentMoralOut.getStates()));
                }
                if (studentMoralOut.getComprehensiveQualityStates()!=null && !"".equals(studentMoralOut.getComprehensiveQualityStates())){
                    list.add(cb.equal(root.get("comprehensiveQualityStates"),studentMoralOut.getComprehensiveQualityStates()));
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
     * @param studentMoralOut
     * @param pageable
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(StudentMoralOut studentMoralOut, Pageable pageable) {
        try {
            Page<StudentMoralOut> page = studentMoralOutRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("deleted"),false),
                                cb.equal(root.get("major"),studentMoralOut.getMajor()),
                                cb.equal(root.get("grade"),studentMoralOut.getGrade())
                        )
                );
                list.add(
                                cb.equal(root.get("studentClass"),studentMoralOut.getStudentClass())
                );
                list.add(
                            cb.equal(root.get("states"),studentMoralOut.getStates())
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
     * @param studentMoralOut
     * @description  更新
     * **/
    @Override
    public Commes update(StudentMoralOut studentMoralOut) {
        try {
            StudentMoralOut studentMoralOut1 = studentMoralOutRespority.findByIdAndDeletedIsFalse(studentMoralOut.getId());
            if (studentMoralOut1!=null){
                studentMoralOut1.setMoralOutType(studentMoralOut.getMoralOutType());
                studentMoralOut1.setMoralOutName(studentMoralOut.getMoralOutName());
                studentMoralOut1.setYear(studentMoralOut.getYear());
                studentMoralOut1.setMoralOutScore(findMoralOutScore(studentMoralOut.getMoralOutName(),studentMoralOut.getMoralOutType()));
                studentMoralOutRespority.save(studentMoralOut1);
                return Commes.successMes();
            }else {
                return Commes.errorMes("402","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }


}

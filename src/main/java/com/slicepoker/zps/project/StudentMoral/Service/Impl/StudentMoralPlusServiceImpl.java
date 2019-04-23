package com.slicepoker.zps.project.StudentMoral.Service.Impl;


import com.slicepoker.zps.project.Moral.Respority.MoralPlusRespority;
import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralPlusRespority;
import com.slicepoker.zps.project.StudentMoral.Service.StudentMoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author Zps
 * @date 2019/3/6 16:21
 **/
@Service
public class StudentMoralPlusServiceImpl implements StudentMoralPlusService {

    @Autowired
    private StudentMoralPlusRespority studentMoralPlusRespority;

    @Autowired
    private MoralPlusRespority moralPlusRespority;


    /**
     * @param studentMoralPlus
     * @description新增或编辑
     * **/
    @Override
    public Commes add(StudentMoralPlus studentMoralPlus) {
        try {
            if (studentMoralPlus.getId()==null){
                studentMoralPlus.setUpdateDate(new Date());
                studentMoralPlus.setStates("MP001");
                studentMoralPlus.setMoralPlusScore(findScore(studentMoralPlus.getMoralPlusType(),studentMoralPlus.getMoralPlusName()));
                return Commes.success(studentMoralPlusRespority.save(studentMoralPlus));
            }else {
               return Commes.errorMes("402","id不为null");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","保存失败");
        }
    }

    /**
     * @param studentMoralPlus
     * **/
    @Override
    public Commes findStudentMoralPlus(StudentMoralPlus studentMoralPlus, Pageable pageable) {
        try {
            Page<StudentMoralPlus> page = studentMoralPlusRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (studentMoralPlus.getStudentNumber()!=null && !"".equals(studentMoralPlus.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),studentMoralPlus.getStudentNumber()));
                }
                if (studentMoralPlus.getStates()!=null && !"".equals(studentMoralPlus.getStates())){
                    list.add(cb.equal(root.get("states"),studentMoralPlus.getStates()));
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
            return Commes.errorMes("401","查询错误");
        }
    }

    private double findScore(String moralPlusType, String moralPlusName){
        double score = moralPlusRespority.find(moralPlusType, moralPlusName);
        return score;
    }

    /**
     * @param studentMoralPlus
     * @param pageable
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(StudentMoralPlus studentMoralPlus, Pageable pageable) {
        try {
            Page<StudentMoralPlus> page = studentMoralPlusRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("grade"),studentMoralPlus.getGrade()),
                                cb.equal(root.get("deleted"),false),
                                cb.equal(root.get("states"),studentMoralPlus.getStates()),
                                cb.or(
                                        cb.equal(root.get("studentName"),studentMoralPlus.getStudentName()),
                                        cb.equal(root.get("studentClass"),studentMoralPlus.getStudentClass()),
                                        cb.equal(root.get("year"),studentMoralPlus.getYear())
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
     * @description 审核
     * **/
    @Override
    public Commes setStates(StudentMoralPlus studentMoralPlus) {
        try {
            StudentMoralPlus studentMoralPlus1 = studentMoralPlusRespority.findByIdAndStatesAndDeletedIsFalse(studentMoralPlus.getId(),"MP001");
            if (studentMoralPlus1!=null){
                studentMoralPlus1.setValue(studentMoralPlus.getValue());
                studentMoralPlus1.setStates(studentMoralPlus.getStates());
                studentMoralPlus1.setApplyPersonName(studentMoralPlus.getApplyPersonName());
                studentMoralPlus1.setApplyPersonNumber(studentMoralPlus.getApplyPersonNumber());
                return Commes.success(studentMoralPlusRespority.saveAndFlush(studentMoralPlus1));
            }else {
                return Commes.errorMes("402","对象不存在");
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
            StudentMoralPlus studentMoralPlus = studentMoralPlusRespority.findByIdAndDeletedIsFalse(id);
            if (studentMoralPlus!=null){
                studentMoralPlus.setDeleted(true);
                return Commes.success(studentMoralPlusRespority.save(studentMoralPlus));
            }else {
                return Commes.errorMes("402","对象未找到");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","删除失败");
        }
    }

    /**
     * @description编辑
     * @param studentMoralPlus
     * **/
    @Override
    public Commes update(StudentMoralPlus studentMoralPlus) {
        try {
            StudentMoralPlus studentMoralPlus1 = studentMoralPlusRespority.findByIdAndStatesAndDeletedIsFalse(studentMoralPlus.getId(),studentMoralPlus.getStates());
            if (studentMoralPlus1!=null){
                studentMoralPlus1.setMoralPlusType(studentMoralPlus.getMoralPlusType());
                studentMoralPlus1.setMoralPlusName(studentMoralPlus.getMoralPlusName());
                studentMoralPlus1.setMoralPlusScore(findScore(studentMoralPlus.getMoralPlusType(),studentMoralPlus.getMoralPlusName()));
                studentMoralPlus1.setStates("MP001");
                return Commes.success(studentMoralPlusRespority.saveAndFlush(studentMoralPlus1));
            }else {
                return Commes.errorMes("402","没有该条数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","编辑失败");
        }
    }

    /**
     * @description 搜索
     * @param studentMoralPlus
     * **/
    @Override
    public Commes search(StudentMoralPlus studentMoralPlus,Pageable pageable) {
        try {
            Page<StudentMoralPlus> page = studentMoralPlusRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                  cb.and(
                          cb.equal(root.get("grade"),studentMoralPlus.getGrade()),
                          cb.equal(root.get("states"),studentMoralPlus.getStates()),
                          cb.equal(root.get("studentClass"),studentMoralPlus.getStudentClass())
                  )
                );
                if (studentMoralPlus.getKeyWord()!=null && !"".equals(studentMoralPlus.getKeyWord())){
                    list.add(
                            cb.or(
                                    cb.equal(root.get("studentName"),studentMoralPlus.getKeyWord()),
                                    cb.equal(root.get("year"),studentMoralPlus.getKeyWord())
                            )
                    );
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","搜索失败");
        }
    }


}

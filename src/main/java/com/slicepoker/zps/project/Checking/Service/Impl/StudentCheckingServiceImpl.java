package com.slicepoker.zps.project.Checking.Service.Impl;

import com.slicepoker.zps.project.Checking.Pojo.StudentChecking;
import com.slicepoker.zps.project.Checking.Respority.StudentCheckingRespority;
import com.slicepoker.zps.project.Checking.Service.StudentCheckingService;
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
 * @date 2019/1/22 17:14
 **/
@Service
public class StudentCheckingServiceImpl implements StudentCheckingService {

   @Autowired
    private StudentCheckingRespority studentCheckingRespority;

   /**
    * @param studentChecking
    * @description 更新考勤信息
    * **/
    @Override
    public Commes update(StudentChecking studentChecking) {
        try {
            if (studentChecking!=null && !"".equals(studentChecking)){
                studentChecking.setUpdateDate(new Date());
                return Commes.success(studentCheckingRespority.save(studentChecking));
            }else {
                return Commes.errorMes("402","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","error");
        }
    }


    @Override
    public Commes get(Pageable pageable) {
        try {
            Page<StudentChecking> page = studentCheckingRespority.findByDeletedIsFalse(pageable);
            if (page.getSize()>0){
                return Commes.success(page);
            }else {
                return Commes.errorMes("402","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查找失败");
        }
    }

    /**
     * @param studentChecking
     * @param pageable
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(StudentChecking studentChecking, Pageable pageable) {
        try {
            Page<StudentChecking> page = studentCheckingRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (studentChecking.getKeyWord()!=null && !"".equals(studentChecking.getKeyWord())){
                    if (studentChecking.getKeyWord().length() ==12){
                        Long findWord = Long.parseLong(studentChecking.getKeyWord());
                        list.add(cb.equal(root.get("studentNumber"),findWord));
                    }else {
                    list.add(
                      cb.or(
                              cb.like(root.get("studentName"),studentChecking.getKeyWord()),
                              cb.like(root.get("studentClass"),"%" + studentChecking.getKeyWord() + "%")
                      )
                    );
                    }
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
     * @param pageable
     * @param studentNumber
     * @description 获取个人考勤信息
     * **/
    @Override
    public Commes getPersonalData(Long studentNumber, Pageable pageable) {
        try {
            Page<StudentChecking> page = studentCheckingRespority.findByStudentNumberAndDeletedIsFalse(studentNumber,pageable);
            if (page.getSize()>0){
                return Commes.success(page);
            }else {
                return Commes.errorMes("402","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }

    /**
     * @param pageable
     * @param major
     * @description 获取专业考勤情况
     * **/
    @Override
    public Commes getMajorData(String major, Pageable pageable) {
        try {
            Page<StudentChecking> page = studentCheckingRespority.findByMajorAndDeletedIsFalse(major, pageable);
            if (page.getSize()>0){
                return Commes.success(page);
            }else {
                return Commes.errorMes("402","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }


    /**
     * @param pageable
     * @param studentChecking
     * @description 同专业下的模糊查询
     * **/
    @Override
    public Commes findFuzzyByMajor(StudentChecking studentChecking, Pageable pageable) {
        try{
            Page<StudentChecking> page = studentCheckingRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("major"),studentChecking.getMajor()),
                                cb.equal(root.get("grade"),studentChecking.getGrade())
                        )
                );
                if (studentChecking.getKeyWord()!=null && !"".equals(studentChecking.getKeyWord())){
                    list.add(
                            cb.or(
                                    cb.like(root.get("studentName"),"%" + studentChecking.getKeyWord()),
                                    cb.equal(root.get("studentClass"),studentChecking.getKeyWord())
                                    )
                    );
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
     * @param studentChecking
     * @param pageable
     * @description 查找未删除拥有删除请求的考勤数据
     * **/
    @Override
    public Commes findDeletedApply(StudentChecking studentChecking, Pageable pageable) {
       try {
           Page<StudentChecking> page = studentCheckingRespority.findAll(((root, query, cb) ->{
               List<Predicate> list = new ArrayList<>();
               list.add(
                 cb.and(
                         cb.equal(root.get("grade"),studentChecking.getGrade()),
                         cb.equal(root.get("deleted"),false),
                         cb.equal(root.get("deletedApply"),true)
                 )
               );
               if (studentChecking.getKeyWord()!=null && !"".equals(studentChecking.getKeyWord())){
                        list.add(
                                cb.or(
                                        cb.equal(root.get("studentName"),studentChecking.getKeyWord()),
                                        cb.like(root.get("studentClass"),"%" + studentChecking.getKeyWord() + "%")
                                )
                        );
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
     * @param id
     * @description 逻辑删除考勤数据
     * **/
    @Override
    public Commes deleteStudentChecking(Long id) {
        try {
           StudentChecking studentChecking = studentCheckingRespority.findByIdAndDeletedIsFalseAndDeletedApplyIsTrue(id);
           if (studentChecking!=null){
               studentChecking.setDeleted(true);
               studentChecking.setDeletedApply(false);
               studentChecking.setDeletedApplyState("DA201903");
               return Commes.success(studentCheckingRespority.save(studentChecking));
           }else {
               return Commes.errorMes("402","查找失败");
           }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","删除失败");
        }
    }

    /**
     * @param id
     * @description 驳回删除考勤数据请求
     * **/
    @Override
    public Commes rejectApply(Long id) {
        try {
            StudentChecking studentChecking = studentCheckingRespority.findByIdAndDeletedIsFalseAndDeletedApplyIsTrue(id);
            if (studentChecking!=null){
                studentChecking.setDeletedApply(false);
                studentChecking.setDeletedApplyState("DA201902");
                return Commes.success(studentCheckingRespority.save(studentChecking));
            }else {
                return Commes.errorMes("402","查找失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","驳回失败");
        }

    }
}

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
    public Commes update(StudentMoralPlus studentMoralPlus) {
        try {
            if (studentMoralPlus.getId()==null){
                studentMoralPlus.setUpdateDate(new Date());
                studentMoralPlus.setStates("MP001");
                studentMoralPlus.setMoralPlusScore(findScore(studentMoralPlus.getMoralPlusType(),studentMoralPlus.getMoralPlusName()));
                return Commes.success(studentMoralPlusRespority.save(studentMoralPlus));
            }else {
                studentMoralPlus.setStates("MP001");
                return Commes.success(studentMoralPlusRespority.save(studentMoralPlus));
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
                list.add(
                        cb.and(
                                cb.equal(root.get("deleted"),false),
                                cb.equal(root.get("studentNumber"),studentMoralPlus.getStudentNumber())
                        )
                );
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

}

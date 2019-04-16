package com.slicepoker.zps.project.Moral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralDeduction;
import com.slicepoker.zps.project.Moral.Respority.MoralDeductionRespority;
import com.slicepoker.zps.project.Moral.Service.MoralDeductionService;
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
 * @date 2018/11/28 16:06
 * @description 德育减分项目实现类
 **/
@Service
public class MoralDeductionServiceImpl implements MoralDeductionService {

    @Autowired
    private MoralDeductionRespority moralDeductionRespority;

    /**
     * @param id
     * 逻辑删除德育减分项目
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            MoralDeduction moralDeduction = moralDeductionRespority.findByIdAndDeletedIsFalse(id);
            if (moralDeduction!=null){
                moralDeduction.setDeleted(true);
                return Commes.success(moralDeductionRespority.save(moralDeduction));
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","");
        }
    }

    /**
     * 查找德育减分项目所有数据
     * **/
    @Override
    public Commes findAll() {
        try {
            List<MoralDeduction> list = moralDeductionRespority.findAll();
            if (!list.isEmpty()){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }

    /**
     * @param moralDeduction
     * 新增或修改德育减分项目
     * **/
    @Override
    public Commes add(MoralDeduction moralDeduction) {
        try {
            if (moralDeduction!=null){
                moralDeduction.setCreateTime(new Date());
                return Commes.success(moralDeductionRespority.save(moralDeduction));
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","保存失败");
        }
    }

    /**
     * @param moralDeductionType
     * 根据德育减分项目类型查找未删除的德育减分项目名称
     * **/
    @Override
    public Commes findByType(String moralDeductionType) {
        try {
            List list = moralDeductionRespority.findMoralDeductionByMoralDeductionType(moralDeductionType);
            if (!list.isEmpty()){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }

    /**
     * 查找全部未删除的德育减分项目名称
     * **/
    @Override
    public Commes findMoralDeduction() {
        try {
            List<MoralDeduction> list = moralDeductionRespority.findMoralDeduction();
            if (!list.isEmpty()){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }

    /**
     * 查询未删除的德育减分项目类型
     * **/
    @Override
    public Commes findMoralDeductionType() {
        try {
            List<MoralDeduction> list = moralDeductionRespority.findMoralDeductionType();
            if (!list.isEmpty()){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }


    /**
     * @param moralDeduction
     * @param pageable
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(MoralDeduction moralDeduction, Pageable pageable) {
        try{
            Page<MoralDeduction> page = moralDeductionRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                    list.add(cb.equal(root.get("deleted"),false));
                if (moralDeduction.getKeyWord()!=null && !"".equals(moralDeduction.getKeyWord())){
                    list.add(
                            cb.or(
                                    cb.like(root.get("moralDeductionName"),"%" + moralDeduction.getKeyWord() + "%"),
                                    cb.like(root.get("moralDeductionType"),"%" + moralDeduction.getKeyWord() + "%")
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


    @Override
    public MoralDeduction findByMoralDeductionType(String moralDeductionType) {
        try {
            MoralDeduction moralDeduction = moralDeductionRespority.findByMoralDeductionTypeAndDeletedIsFalse(moralDeductionType);
            if (moralDeduction!=null){
                return moralDeduction;
            }else {
                return null;
            }
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}

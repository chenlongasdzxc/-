package com.slicepoker.zps.project.Moral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import com.slicepoker.zps.project.Moral.Respority.MoralPlusRespority;
import com.slicepoker.zps.project.Moral.Service.MoralPlusService;
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
 * @date 2018/11/28 14:12
 * @description 德育加分项目实现方法
 **/
@Service
public class MoralPlusServiceImpl implements MoralPlusService {

    @Autowired
    private MoralPlusRespority moralPlusRespority;


    /***
     * 查找全部
     * */
    @Override
    public Commes findAll() {
        try {
            List<MoralPlus> list = moralPlusRespority.findByDeletedIsFalse();
            if (list.size()>0){
            return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查找失败");
        }
    }

    /**
     * @param id
     * @description 删除德育加分数据字典
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            MoralPlus moralPlus = moralPlusRespority.findByIdAndDeletedIsFalse(id);
            if (moralPlus!=null){
                moralPlus.setDeleted(true);
                return Commes.success(moralPlusRespority.save(moralPlus));
            }else {
                return Commes.errorMes("401","数据不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","删除失败");
        }
    }

    @Override
    public Commes add(MoralPlus moralPlus) {
        try {
             if(moralPlus!=null){
                 return Commes.success(moralPlusRespority.save(moralPlus));
             }else {
                 return Commes.errorMes("401","参数为空");
             }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","保存失败");
        }
    }

    /**
     * @description 查找德育加分类型
     * **/
    @Override
    public Commes find() {
        try {
            List<MoralPlus> list = moralPlusRespority.findMoralPlus();
            if (!list.isEmpty()){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查找失败");
        }
    }


    /**
     * @description模糊查询
     * @param moralPlus
     * @param pageable
     * **/
    @Override
    public Commes findFuzzy(MoralPlus moralPlus, Pageable pageable) {
        try {
            Page<MoralPlus> page = moralPlusRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                    list.add(cb.equal(root.get("deleted"),false));
                if (moralPlus.getKeyWord()!=null && !"".equals(moralPlus.getKeyWord())){
                    list.add(
                            cb.or(
                                    cb.like(root.get("moralPlusType"),"%" + moralPlus.getKeyWord() + "%"  ),
                                    cb.like(root.get("moralPlusName"),"%" + moralPlus.getKeyWord() + "%")
                                    )
                            );
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","没有数据");
        }
    }
}

package com.slicepoker.zps.project.Moral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import com.slicepoker.zps.project.Moral.Respority.MoralOutRespority;
import com.slicepoker.zps.project.Moral.Service.MoralOutService;
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
 * @date 2018/11/29 9:16
 * @description 课外加分实现方法
 **/
@Service
public class MoralOutServiceImpl implements MoralOutService {

    @Autowired
    private MoralOutRespority moralOutRespority;

    /**
     * 查找全部未删除数据
     * **/
    @Override
    public Commes findAll() {
        try {
            List list = moralOutRespority.findAllMoralName();
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
     * @param id
     * 删除
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            MoralOut moralOut = moralOutRespority.findByIdAndDeletedIsFalse(id);
            if (moralOut!=null){
                moralOut.setDeleted(true);
                return Commes.success(moralOutRespority.save(moralOut));
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","删除失败");
        }
    }

    /**
     * @param moralOut
     * 新增或编辑课外加分项目
     * **/
    @Override
    public Commes update(MoralOut moralOut) {
        try {
           if (moralOut.getId()!=null){
               return Commes.success(moralOutRespority.save(moralOut));
           }else {
               MoralOut moralOut1 = moralOutRespority.findByMoralOutNameAndDeletedIsFalse(moralOut.getMoralOutName());
                if (moralOut1!=null){
                    return Commes.errorMes("401","保存失败，已有该条数据");
                }else {
                    moralOut.setCreateTime(new Date());
                    return Commes.success(moralOutRespority.saveAndFlush(moralOut));
                }
           }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","保存失败");
        }
    }


    /**
     * @param moralOutType
     * 根据课外加分项目类型查找
     * **/
    @Override
    public Commes findByType(String moralOutType) {
        try {
            List list = moralOutRespority.findByType(moralOutType);
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
     * @param moralOut
     * @description模糊查询
     * **/
    @Override
    public Commes findFuzzy(MoralOut moralOut, Pageable pageable) {
       try {
           Page<MoralOut> page = moralOutRespority.findAll(((root, query, cb) -> {
               List<Predicate> list = new ArrayList<>();
               list.add(cb.equal(root.get("deleted"),false));
               if (moralOut.getKeyWord()!=null && !"".equals(moralOut.getKeyWord())){
                   list.add(
                           cb.or(
                                   cb.equal(root.get("moralOutName"),moralOut.getKeyWord()),
                                   cb.equal(root.get("moralOutType"),moralOut.getKeyWord())
                           )
                   );
               }
               return cb.and(list.toArray(new Predicate[list.size()]));
           }),pageable);
           return Commes.success(page);
       }catch (Exception e){
           e.printStackTrace();
           return Commes.errorMes("402","查询失败");
       }
    }
}

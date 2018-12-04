package com.slicepoker.zps.project.Moral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import com.slicepoker.zps.project.Moral.Respority.MoralOutRespority;
import com.slicepoker.zps.project.Moral.Service.MoralOutService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/29 9:16
 * @description 课外加分实现方法
 **/
@Service
public class MoralServiceImpl implements MoralOutService {

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
    public Commes add(MoralOut moralOut) {
        try {
            if (moralOut!=null){
            return Commes.success(moralOutRespority.save(moralOut));
            }else {
                return Commes.errorMes("401","数据为空");
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
}

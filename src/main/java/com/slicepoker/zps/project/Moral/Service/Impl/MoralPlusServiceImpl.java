package com.slicepoker.zps.project.Moral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import com.slicepoker.zps.project.Moral.Respority.MoralPlusRespority;
import com.slicepoker.zps.project.Moral.Service.MoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            List<MoralPlus> list = moralPlusRespority.findAll();
            return Commes.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查找失败");
        }
    }

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
     *
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
}

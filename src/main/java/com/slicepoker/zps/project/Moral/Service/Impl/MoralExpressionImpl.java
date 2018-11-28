package com.slicepoker.zps.project.Moral.Service.Impl;

import com.slicepoker.zps.project.Moral.Pojo.MoralExpression;
import com.slicepoker.zps.project.Moral.Respority.MoralExpressionRespority;
import com.slicepoker.zps.project.Moral.Service.MoralExpressionService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Zps
 * @date 2018/11/26 17:25
 * @description 德育表现实现方法
 **/
@Service
public class MoralExpressionImpl implements MoralExpressionService {

    @Autowired
    private MoralExpressionRespority moralExpressionRespority;


    /**
     * @param moralExpression
     *  新增或修改德育表现
     * **/
    @Override
    public Commes add(MoralExpression moralExpression) {
        try {
            if (moralExpression!=null) {
                moralExpression.setCreateDate(new Date());
                return Commes.success(moralExpressionRespority.save(moralExpression));
            }else {
                return Commes.errorMes("405","数据为空，保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","新增或编辑失败");
        }
    }

    /**
     * @param id
     * 删除德育表现
     * **/

    @Override
    public Commes delete(Long id) {
        try {
            MoralExpression moralExpression = moralExpressionRespority.findByIdAndDeletedIsFalse(id);
            if (moralExpression!=null){
                moralExpression.setDeleted(true);
                return Commes.success(moralExpressionRespority.save(moralExpression));
            }else {
                return Commes.errorMes("405","不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","删除失败");
        }
    }


    /**
     * 查找全部德育表现
     * **/
    @Override
    public Commes findAll() {
        try {
            List<MoralExpression> list = moralExpressionRespority.findAll();
            return Commes.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","查找失败");
        }
    }
}

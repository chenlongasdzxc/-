package com.slicepoker.zps.project.Sketch.Service.Impl;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import com.slicepoker.zps.project.Sketch.Respority.SketchScoreRespority;
import com.slicepoker.zps.project.Sketch.Service.SketchScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/2 15:12
 **/
@Service
public class SketchScoreServiceImpl implements SketchScoreService {

    @Autowired
    private SketchScoreRespority sketchScoreRespority;

    /**新增或编辑素拓分类型
     * @param sketchScore
     * **/
    @Override
    public Commes addSketch(SketchScore sketchScore) {
        try {
            SketchScore sketchScore1 = sketchScoreRespority.findByTypeAndDeletedIsFalse(sketchScore.getType());
            if (sketchScore1!=null){
                return Commes.errorMes("400","类型已存在");
            }else {
                return Commes.success(sketchScoreRespority.save(sketchScore));
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","失败");
        }
    }

    /**
    *根据素拓分类型删除素拓分类型
     * @param type
    * */

    @Override
    public Commes deleteSketch(String type) {
        try {
            SketchScore sketchScore = sketchScoreRespority.findByTypeAndDeletedIsFalse(type);
            if (sketchScore!=null){
                sketchScore.setDeleted(true);
                return Commes.success(sketchScoreRespority.save(sketchScore));
            }else {
                return Commes.errorMes("400","该类型不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","删除失败");
        }
    }

    /**
    * 查询所有素拓分类型
     *
    * */

    @Override
    public Commes findFuzzy() {
        try {
            List<SketchScore> list= sketchScoreRespority.findAll();
            if (list.size()!=0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("400","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","error");
        }

    }
}

package com.slicepoker.zps.project.Sketch.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import com.slicepoker.zps.project.Sketch.Respority.SketchScoreRespority;
import com.slicepoker.zps.project.Sketch.Service.SketchScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
            if (sketchScore.getType()!=null && !"".equals(sketchScore.getType())){
                SketchScore sketchScore1 = sketchScoreRespority.findByTypeAndDeletedIsFalse(sketchScore.getType());
                if (sketchScore1 == null){
                    return Commes.success(sketchScoreRespority.save(sketchScore));
                }
                if(sketchScore.getId()!=null){
                    return Commes.success(sketchScoreRespority.save(sketchScore));
                }else {
                    return Commes.errorMes("401","数据已存在");
                }
            }else {
                return Commes.errorMes("402","数据为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","保存失败");
        }
    }

    /**
    *根据id删除素拓分类型
     * @param id
    * */

    @Override
    public Commes deleteSketch(Long id) {
        try {
            SketchScore sketchScore = sketchScoreRespority.findByIdAndDeletedIsFalse(id);
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
    * 查询未删除所有素拓分类型
    * */
    @Override
    public Commes findFuzzy() {
        try {
            List<SketchScore> list= sketchScoreRespority.findAllByDeletedIsFalse();
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


    @Override
    public Commes findTypeList() {
        return null;
    }


    @Override
    public Commes findfuzzy(SketchScore sketchScore, Pageable pageable) {
        try {
            Page<SketchScore> page = sketchScoreRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (sketchScore.getKeyWord()!=null && !"".equals(sketchScore.getKeyWord())){
                    list.add(
                            cb.or(
                                    cb.like(root.get("type"),"%" + sketchScore.getKeyWord() + "%")
                            )
                    );
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","error");
        }
    }
}

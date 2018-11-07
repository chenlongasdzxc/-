package com.slicepoker.zps.project.Service.Impl;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Pojo.Sketch;
import com.slicepoker.zps.project.Pojo.SketchScore;
import com.slicepoker.zps.project.Pojo.User;
import com.slicepoker.zps.project.Respority.SketchRespority;
import com.slicepoker.zps.project.Respority.SketchScoreRespority;
import com.slicepoker.zps.project.Respority.UserRespority;
import com.slicepoker.zps.project.Service.SketchService;
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
 * @date 2018/11/2 14:08
 **/
@Service
public class SketchServiceImpl implements SketchService {

    @Autowired
    private SketchRespority sketchRespority;

    @Autowired
    private UserRespority userRespority;

    @Autowired
    private SketchScoreRespority sketchScoreRespority;

    /**
     * @param sketch
     * */
    @Override
    public Commes updateSketch(Sketch sketch) {
        try{
            double sketchnumber = sketch.getSketchScore();
            Sketch sketch1 = sketchRespority.findByIdAndDeletedIsFalseAndAndSketchStatesIsFalse(sketch.getId());
            if (sketch1==null){
           SketchScore sketchScore = sketchScoreRespority.findByTypeAndDeletedIsFalse(sketch.getType());
           if (sketchScore!=null){
                sketch.setSketchTypeCode(sketchScore.getSketchTypeCode());
                setSketchPart(sketch,sketchnumber);
                return Commes.success(sketchRespority.save(sketch));
           }else {
               return Commes.errorMes("400","查询失败");
           }
            }else {
                sketch1 = setSketchPart(sketch,sketchnumber);
                return Commes.success(sketchRespority.save(sketch1));
            }
        }catch (Exception e){
                e.printStackTrace();
                return Commes.errorMes("500","error");
        }
    }

    /**
     * @param  studentNumber 学号
     * @param createDateStart
     * @param createDateStop
     * */
    @Override
    public Commes findSketch(Long studentNumber, Date createDateStart, Date createDateStop, Pageable pageable) {
        try {
            Page<Sketch> page = sketchRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                if (studentNumber!=null){
                    list.add(cb.equal(root.get("studentNumber"),studentNumber));
                }
                if (createDateStart!=null&&createDateStop!=null){
                    list.add(cb.between(root.get("createDate"),createDateStart,createDateStop));
                }
                if (createDateStart==null&&createDateStop!=null){
                    list.add(cb.equal(root.get("createDate"),createDateStop));
                }
                if (createDateStart!=null&&createDateStop==null){
                    list.add(cb.equal(root.get("createDate"),createDateStart));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","查询失败");
        }
    }

    /**
     * @param  className 班级名称
     * */
    @Override
    public Commes findByClass(String className) {
        try {
            List<Sketch> list = sketchRespority.findByClassNameAndDeletedIsFalse(className);
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("400","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","查询失败");
        }
    }


    /**
     * @param id  id
     * @param states 状态
     *  true 已审核
     *  默认  false  未审核
     * */
    @Override
    public Commes setStates(Long id, String states) {
        try {
            Sketch sketch = sketchRespority.findByIdAndDeletedIsFalse(id);
            if (sketch!=null){
                sketch.setSketchStates(true);
                return Commes.success(sketchRespority.save(sketch));
            }else {
                return Commes.errorMes("500","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","error");
        }
    }



    public Sketch setSketchPart(Sketch sketch,double sketchnumber){
            if (sketchnumber==0.3){
                sketch.setSketchPart("参与者");
            }
            if (sketchnumber==0.5){
                sketch.setSketchPart("组织者");
            }
            if (sketchnumber==1||sketchnumber==1.5||sketchnumber==2){
                sketch.setSketchPart("获奖者");
            }
            return sketch;

    }
}

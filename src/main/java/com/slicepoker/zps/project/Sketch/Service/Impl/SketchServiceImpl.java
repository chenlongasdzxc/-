package com.slicepoker.zps.project.Sketch.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import com.slicepoker.zps.project.Sketch.Respority.SketchRespority;
import com.slicepoker.zps.project.Sketch.Respority.SketchScoreRespority;
import com.slicepoker.zps.project.User.Respority.UserRespority;
import com.slicepoker.zps.project.Sketch.Service.SketchService;
import com.slicepoker.zps.project.Util.SketchUtil;
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
    private SketchUtil sketchUtil;

    @Autowired
    private UserRespority userRespority;

    @Autowired
    private SketchScoreRespority sketchScoreRespority;

    /**
     * 更新素拓分
     * @param sketch
     * */
    @Override
    public Commes updateSketch(Sketch sketch) {
        try{
            String sketchPart = sketch.getSketchPart();
            /*查询id是否存在*/
            Sketch sketch1 = sketchRespority.findByIdAndDeletedIsFalseAndSketchStatesIsFalse(sketch.getId());
            if (sketch1==null){
                SketchScore sketchScore = sketchScoreRespority.findByTypeAndDeletedIsFalse(sketch.getType());
                    if (sketchScore!=null){
                        sketch.setSketchScore(sketchUtil.setSketch(sketchPart));
                        sketch.setCreateDate(new Date());
                        return Commes.success(sketchRespority.save(sketch));
                    }else {
               return Commes.errorMes("400","查询失败"); }
            }else {
                return Commes.success(sketchRespority.save(sketch1));
            }
        }catch (Exception e){
                e.printStackTrace();
                return Commes.errorMes("500","error");
        }
    }

    /**
     * 根据学号、创建时间查询素拓分
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
     * 根据班级名称查询素拓分
     * @param  className 班级名称
     * */
    @Override
    public Commes findByClass(String className) {
        try {
            List<Sketch> list = sketchRespority.findByStudentClassAndDeletedIsFalseAndAndSketchStatesIsTrue(className);
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
     * 审核
     * @param id  id
     *  true 已审核
     *  默认  false  未审核
     * */
    @Override
    public Commes setStates(Long id) {
        try {
            Sketch sketch = sketchRespority.findByIdAndDeletedIsFalseAndSketchStatesIsFalse(id);
            if (sketch!=null){
                sketch.setSketchStates(true);
                return Commes.success(sketchRespority.save(sketch));
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","审核失败");
        }
    }


    /**
     * 查询所有素拓分
     * **/
    @Override
    public Commes findAll() {
        try {
            List<Sketch> list = sketchRespority.findAll();
            return Commes.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }

    /**
     * @param studentNumber
     * 根据学号查询该学号所有素拓分
     * **/
    @Override
    public Commes findByStudentNumber(Long studentNumber) {
        try {
            List<Sketch> list = sketchRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (list!=null){
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
     *根据审核状态查询素拓分
     * **/
    @Override
    public Commes findByStates() {
        try {
            List<Sketch> list = sketchRespority.findBySketchStatesIsFalse();
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
     * @param studentNumber
     * 计算个人素拓分
     * **/
    @Override
    public Commes countSketch(Long studentNumber) {
        try {
            double personalScore = sketchRespority.sumSketchScore(studentNumber);
            return Commes.success(personalScore);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }
}

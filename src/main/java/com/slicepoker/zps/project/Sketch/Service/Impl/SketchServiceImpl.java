package com.slicepoker.zps.project.Sketch.Service.Impl;

import com.slicepoker.zps.project.Sketch.Pojo.SketchAnalysis;
import com.slicepoker.zps.project.Sketch.Service.SketchAnalysisService;
import com.slicepoker.zps.project.Sketch.Service.SketchCountService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import com.slicepoker.zps.project.Sketch.Respority.SketchRespority;
import com.slicepoker.zps.project.Sketch.Respority.SketchScoreRespority;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import com.slicepoker.zps.project.User.Respority.StudentInfoRespority;
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
    private StudentInfoRespority studentInfoRespority;

    @Autowired
    private SketchScoreRespority sketchScoreRespority;

    @Autowired
    private SketchAnalysisService sketchAnalysisService;

    @Autowired
    private SketchCountService sketchCountService;

    /**
     * 更新素拓分
     * @param sketch
     * */
    @Override
    public Commes updateSketch(Sketch sketch) {
        try{
            String sketchPart = sketch.getSketchPart();
                SketchScore sketchScore = sketchScoreRespority.findByTypeAndDeletedIsFalse(sketch.getType());
                    if (sketchScore!=null){
                        sketch.setSketchScore(sketchUtil.setSketch(sketchPart));
                        sketch.setCreateDate(new Date());
                        sketch.setSketchStates("SK001"); //未审核
                        sketchRespority.save(sketch);
                        Double sum = sketchRespority.sumSketchScore(sketch.getStudentNumber(),sketch.getType(),"SK002");
                        if (sum==null){
                            sum = 0.0;
                        }
                        SketchAnalysis sketchAnalysis = new SketchAnalysis();
                        sketchAnalysis.setScoreCount(sum);
                        sketchAnalysis.setSketchTypeName(sketch.getType());
                        sketchAnalysis.setStudentNumber(sketch.getStudentNumber());
                        sketchAnalysis.setGrade(sketch.getGrade());
                        sketchAnalysis.setStudentName(sketch.getStudentName());
                        sketchAnalysis.setMajor(sketch.getMajor());
                        sketchAnalysis.setStudentClass(sketch.getStudentClass());
                        sketchAnalysisService.update(sketchAnalysis);
                        sketchCountService.undateCount(sketch);
                        return Commes.successMes();
                    }else {
               return Commes.errorMes("400","查询失败"); }
        }catch (Exception e){
                e.printStackTrace();
                return Commes.errorMes("500","error");
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
     * @param sketch
     * @description 审核
     * */
    @Override
    public Commes setStates(Sketch sketch) {
        try {
            Sketch sketch1 = sketchRespority.findByIdAndDeletedIsFalse(sketch.getId());
            if (sketch1!=null){
                sketch1.setSketchStates(sketch.getSketchStates());
                sketch1.setApplyStudentName(sketch.getApplyStudentName());
                sketch1.setApplyStudentNumber(sketch.getApplyStudentNumber());
                sketchRespority.save(sketch1);
                double sum = sketchRespority.sumSketchScore(sketch1.getStudentNumber(),sketch1.getType(),"SK002");
                SketchAnalysis sketchAnalysis = new SketchAnalysis();
                sketchAnalysis.setScoreCount(sum);
                sketchAnalysis.setSketchTypeName(sketch1.getType());
                sketchAnalysis.setStudentNumber(sketch1.getStudentNumber());
                sketchAnalysisService.update(sketchAnalysis);
                sketchCountService.undateCount(sketch1);
                return Commes.successMes();
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
    public Commes findByStudentNumber(Long studentNumber,Pageable pageable) {
        try {
            Page<Sketch> page = sketchRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("deleted"),false),
                                cb.equal(root.get("studentNumber"),studentNumber),
                                cb.or(
                                        cb.equal(root.get("sketchStates"),"SK002")
                                )
                        )

                );
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
           return Commes.success(page);
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
     * @param id
     * @descriptions删除素拓分
     * **/
    @Override
    public Commes delete(Long id) {
        try {
            Sketch sketch = sketchRespority.findByIdAndDeletedIsFalse(id);
            if (sketch!=null){
                sketch.setDeleted(true);
                return Commes.success(sketchRespority.saveAndFlush(sketch));
            }else {
                return Commes.errorMes("401","该条数据不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","删除失败");
        }
    }

    /**
     * @param pageable
     * @param sketch
     * @description 查询同班级未审核或已审核素拓分
     * **/
    @Override
    public Commes findFuzzy(Sketch sketch, Pageable pageable) {
        try {
            Page<Sketch> page = sketchRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("studentClass"),sketch.getStudentClass()),
                                cb.equal(root.get("sketchStates"),sketch.getSketchStates()),
                                cb.equal(root.get("deleted"),false)
                        )
                );
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","查询失败");
        }
    }


    @Override
    public Commes getClassSum(String studentClass) {
        try {
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","计算失败");
        }
    }
}

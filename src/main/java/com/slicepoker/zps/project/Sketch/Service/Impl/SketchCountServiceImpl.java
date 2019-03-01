package com.slicepoker.zps.project.Sketch.Service.Impl;

import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import com.slicepoker.zps.project.Sketch.Pojo.SketchCount;
import com.slicepoker.zps.project.Sketch.Respority.SketchCountRespority;
import com.slicepoker.zps.project.Sketch.Respority.SketchRespority;
import com.slicepoker.zps.project.Sketch.Service.SketchCountService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.xml.ws.soap.Addressing;
import java.util.Date;

/**
 * @author Zps
 * @date 2019/3/1 13:55
 **/
@Service
public class SketchCountServiceImpl implements SketchCountService {

    @Autowired
    private SketchCountRespority sketchCountRespority;
    @Autowired
    private SketchRespority sketchRespority;

    /**
     * @param sketch
     * @description 计算素拓分总分
     * **/
    public Commes undateCount(Sketch sketch){
        try {
            SketchCount sketchCount = sketchCountRespority.findByStudentNumber(sketch.getStudentNumber());
            if (sketchCount!=null){
                sketchCount.setSketchScore(sketchRespority.sum(sketch.getStudentNumber(),"SK002"));
                sketchCount.setYear(new Date());
                return Commes.success(sketchCountRespority.saveAndFlush(sketchCount));
            } else {
                SketchCount sketchCount1 = new SketchCount();
                sketchCount1.setSketchScore(sketchRespority.sum(sketch.getStudentNumber(),"SK002"));
                sketchCount1.setYear(new Date());
                sketchCount1.setGrade(sketch.getGrade());
                sketchCount1.setMajor(sketch.getMajor());
                sketchCount1.setStudentClass(sketch.getStudentClass());
                sketchCount1.setStudentName(sketch.getStudentName());
                sketchCount1.setStudentNumber(sketch.getStudentNumber());
                return Commes.success(sketchCountRespority.saveAndFlush(sketchCount1));
            }
        } catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","更新失败");
        }

    }


    @Override
    public Commes getCountData(Pageable pageable) {
        try {
            Page<SketchCount> page = sketchCountRespority.findAll(pageable);
            if (page.getSize()>0){
                return Commes.success(page);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","获取数据失败");
        }
    }
}

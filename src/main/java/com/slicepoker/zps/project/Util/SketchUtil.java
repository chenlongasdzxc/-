package com.slicepoker.zps.project.Util;

import com.slicepoker.zps.project.Sketch.Respority.SketchScoreRespority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author Zps
 * @date 2018/11/21 10:36
 * @description 素拓分工具
 **/
@Service
public class SketchUtil {


    @Autowired
    private SketchScoreRespority sketchScoreRespority;

    /**
     * 根据参与角色设置素拓分
     * @param sketchPart 参与角色
     * **/
    public double setSketch(String sketchPart){
        double sketch;
        if (Objects.equals(sketchPart,"参与者")){
            return sketch=0.3;
        }
        if (Objects.equals(sketchPart,"组织者")){
            return sketch=0.5;
        }
        if (Objects.equals(sketchPart,"获奖者1")){
            return sketch=1.0;
        }
        if (Objects.equals(sketchPart,"获奖者2")){
            return sketch=1.5;
        }
        if (Objects.equals(sketchPart,"获奖者3")){
            return sketch=2.0;
        }else {
            return 0;
        }
    }
}

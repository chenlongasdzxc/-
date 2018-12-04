package com.slicepoker.zps.project.Sketch.Controller;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import com.slicepoker.zps.project.Sketch.Service.SketchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/5 17:00
 **/
@RestController
@RequestMapping("/Sketch")
public class SketchController {

    @Autowired
    private SketchService sketchService;
    /**
     * 更新素拓分
     * **/
    @PostMapping("/update")
    public Commes updateSketch(Sketch sketch){
        return sketchService.updateSketch(sketch);
    }


    /**
     * 通过班级查询素拓分List
     * **/
    @GetMapping("/findByClassName")
    public Commes findByClassName(String className){
        return sketchService.findByClass(className);
    }

    /**
     * 审核素拓分
     * **/
    @GetMapping("/set")
    public Commes setSkStates( Long id){
        return sketchService.setStates(id);
    }


    /**
     * 模糊查询
     * **/
    @GetMapping("/findFuzzy")
    public Commes findSketch(Long studentNumber, Date createDateStart, Date createDateStop, Pageable pageable){
        return sketchService.findSketch(studentNumber, createDateStart, createDateStop, pageable);
    }


    /**
     * 查询所有素拓分
     * **/
    @GetMapping("/findAll")
    public Commes findAllSketch(){
        return sketchService.findAll();
    }

    /**
     * 根据学号查询素拓分
     * **/
    @GetMapping("/findByStudentNumber")
    public Commes findByStudentNumber(Long studentNumber){
        return sketchService.findByStudentNumber(studentNumber);
    }

    /**
     * 根据审核状态查询素拓分
     * **/
    @GetMapping("/findByStates")
    public Commes findByStates(){
        return sketchService.findByStates();
    }
}

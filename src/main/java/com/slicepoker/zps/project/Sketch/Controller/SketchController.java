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
     * @description更新素拓分
     * **/
    @PostMapping("/update")
    public Commes updateSketch(@RequestBody Sketch sketch){
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
    public Commes setSkStates( Sketch sketch){
        return sketchService.setStates(sketch);
    }


    /**
     * 模糊查询
     * **/
    @GetMapping("/findFuzzy")
    public Commes findFuzzy(Sketch sketch,Pageable pageable){
        return sketchService.findFuzzy(sketch, pageable);
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
    @GetMapping("/personal")
    public Commes findByStudentNumber(Sketch sketch,Pageable pageable){
        return sketchService.findByStudentNumber(sketch.getStudentNumber(),pageable);
    }

    /**
     * 根据审核状态查询素拓分
     * **/
    @GetMapping("/findByStates")
    public Commes findByStates(){
        return sketchService.findByStates();
    }



    /**
     * @description删除素拓分
     * **/
    @GetMapping("/delete")
    public Commes delete(Long id){
        return sketchService.delete(id);
    }
}

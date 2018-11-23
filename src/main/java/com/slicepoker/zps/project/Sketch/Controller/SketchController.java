package com.slicepoker.zps.project.Sketch.Controller;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import com.slicepoker.zps.project.Sketch.Service.SketchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/5 17:00
 **/
@RestController
public class SketchController {

    @Autowired
    private SketchService sketchService;

    @PostMapping("/updateSketch")
    public Commes updateSketch(Sketch sketch){
        return sketchService.updateSketch(sketch);
    }

    @GetMapping("/fdByCn")
    public Commes findByClassName(String className){
        return sketchService.findByClass(className);
    }

    @GetMapping("/setSkStates")
    public Commes setSkStates(Long id,String states){
        return sketchService.setStates(id, states);
    }

    @GetMapping("/fdSkByDate")
    public Commes findSketch(Long studentNumber, Date createDateStart, Date createDateStop, Pageable pageable){
        return sketchService.findSketch(studentNumber, createDateStart, createDateStop, pageable);
    }

    @GetMapping("/findAllSketch")
    public Commes findAllSketch(){
        return sketchService.findAll();
    }
}

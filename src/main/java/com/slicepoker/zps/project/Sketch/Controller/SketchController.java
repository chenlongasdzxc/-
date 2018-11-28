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

    @PostMapping("/update")
    public Commes updateSketch(@RequestBody Sketch sketch){
        return sketchService.updateSketch(sketch);
    }

    @GetMapping("/findByClassName")
    public Commes findByClassName(@RequestParam String className){
        return sketchService.findByClass(className);
    }

    @GetMapping("/set")
    public Commes setSkStates(@RequestParam Long id,
                              @RequestParam String states){
        return sketchService.setStates(id, states);
    }

    @GetMapping("/findFuzzy")
    public Commes findSketch(@RequestParam Long studentNumber,
                             @RequestParam Date createDateStart,
                             @RequestParam Date createDateStop,
                             @RequestParam Pageable pageable){
        return sketchService.findSketch(studentNumber, createDateStart, createDateStop, pageable);
    }

    @GetMapping("/findAll")
    public Commes findAllSketch(){
        return sketchService.findAll();
    }
}

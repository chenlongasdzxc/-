package com.slicepoker.zps.project.Sketch.Controller;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import com.slicepoker.zps.project.Sketch.Service.SketchScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2018/11/2 15:26
 **/
@RestController
public class SketchScoreController {

    @Autowired
    private SketchScoreService sketchScoreService;

    @PostMapping("/addSketchScore")
    public Commes add(SketchScore sketchScore){
        return sketchScoreService.addSketch(sketchScore);
    }

    @PostMapping("/deleteSketchScore")
    public Commes delete(String type){
        return sketchScoreService.deleteSketch(type);
    }

    @GetMapping("/findType")
    public Commes findFuzzy(){
        return sketchScoreService.findFuzzy();
    }
}

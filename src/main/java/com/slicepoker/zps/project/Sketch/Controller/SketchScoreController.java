package com.slicepoker.zps.project.Sketch.Controller;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import com.slicepoker.zps.project.Sketch.Service.SketchScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Zps
 * @date 2018/11/2 15:26
 **/
@RestController
@RequestMapping("/SketchScore")
public class SketchScoreController {

    @Autowired
    private SketchScoreService sketchScoreService;

    @PostMapping("/add")
    public Commes add(@RequestBody SketchScore sketchScore){
        return sketchScoreService.addSketch(sketchScore);
    }

    @PostMapping("/delete")
    public Commes delete(@RequestParam String type){
        return sketchScoreService.deleteSketch(type);
    }

    @GetMapping("/findFuzzy")
    public Commes findFuzzy(){
        return sketchScoreService.findFuzzy();
    }
}

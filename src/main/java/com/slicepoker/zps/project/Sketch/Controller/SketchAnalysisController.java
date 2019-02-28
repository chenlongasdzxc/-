package com.slicepoker.zps.project.Sketch.Controller;

import com.slicepoker.zps.project.Sketch.Pojo.SketchAnalysis;
import com.slicepoker.zps.project.Sketch.Service.SketchAnalysisService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2019/2/27 15:00
 **/
@RestController
@RequestMapping("/sketchAnalysis")
public class SketchAnalysisController {

    @Autowired
    private SketchAnalysisService sketchAnalysisService;

    @GetMapping("/getPersonal")
    public Commes getPersonal(SketchAnalysis sketchAnalysis){
        return sketchAnalysisService.getPersonal(sketchAnalysis.getStudentNumber());
    }


}

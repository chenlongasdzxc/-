package com.slicepoker.zps.project.Sketch.Controller;

import com.slicepoker.zps.project.Sketch.Service.SketchCountService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2019/3/1 15:36
 **/
@RestController
@RequestMapping("/SketchCount")
public class SketchCountController {

    @Autowired
    private SketchCountService sketchCountService;

    @GetMapping("/getCountData")
    public Commes getCountData(Pageable pageable){
        return sketchCountService.getCountData(pageable);
    }
}

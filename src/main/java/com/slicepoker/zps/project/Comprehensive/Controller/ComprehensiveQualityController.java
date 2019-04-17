package com.slicepoker.zps.project.Comprehensive.Controller;

import com.slicepoker.zps.project.Comprehensive.Service.ComprehensiveQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Zps
 * @date 2019/4/16 16:21
 **/
@RestController
@RequestMapping("/comprehensiveQuality")
public class ComprehensiveQualityController {

    @Autowired
    private ComprehensiveQualityService comprehensiveQualityService;
}

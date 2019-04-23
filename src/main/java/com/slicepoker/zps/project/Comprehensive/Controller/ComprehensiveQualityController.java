package com.slicepoker.zps.project.Comprehensive.Controller;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import com.slicepoker.zps.project.Comprehensive.Service.ComprehensiveQualityService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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


    /**
     * @param comprehensiveQuality
     * @param pageable
     * @description 模糊查询
     * **/
    @GetMapping("/findFuzzy")
    public Commes findFuzzy(ComprehensiveQuality comprehensiveQuality, Pageable pageable){
        return comprehensiveQualityService.findFuzzy(comprehensiveQuality, pageable);
    }

    /**
     * @param states
     * @param id
     * @description 设置状态
     * **/
    @GetMapping("/setStates/{id}/{states}")
    public Commes setStates(@PathVariable(name="id") Long id, @PathVariable(name="states") String states){
        return comprehensiveQualityService.setStates(id, states);
    }

    /**
     * @param studentNumber
     * @description 查找个人综合素质
     * **/
    @GetMapping("/findPersonal/{studentNumber}")
    public Commes findPersonal(@PathVariable(name="studentNumber") Long studentNumber){
        return comprehensiveQualityService.findPersonal(studentNumber);
    }
}

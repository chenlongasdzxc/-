package com.slicepoker.zps.project.Comprehensive.Service.Impl;

import com.slicepoker.zps.project.Comprehensive.Pojo.ComprehensiveQuality;
import com.slicepoker.zps.project.Comprehensive.Respority.ComPrehensiveQualityRespority;
import com.slicepoker.zps.project.Comprehensive.Service.ComprehensiveQualityService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zps
 * @date 2019/4/16 16:20
 **/
@Service
public class ComprehensiveQualityServiceImpl implements ComprehensiveQualityService {

    @Autowired
    private ComPrehensiveQualityRespority comPrehensiveQualityRespority;

    @Override
    public Commes add(ComprehensiveQuality comprehensiveQuality) {
        try {
           ComprehensiveQuality comprehensiveQuality1 = comPrehensiveQualityRespority.findByStudentNumberAndDeletedIsFalse(comprehensiveQuality.getStudentNumber());
           if (comprehensiveQuality1==null){
               comPrehensiveQualityRespority.save(comprehensiveQuality);
               return Commes.successMes();
           }else {
               return Commes.badRequestError();
           }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}

package com.slicepoker.zps.project.Sketch.Service.Impl;

import com.slicepoker.zps.project.Sketch.Pojo.SketchAnalysis;
import com.slicepoker.zps.project.Sketch.Respority.SketchAnalysisRespority;
import com.slicepoker.zps.project.Sketch.Respority.SketchRespority;
import com.slicepoker.zps.project.Sketch.Service.SketchAnalysisService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2019/2/26 16:45
 **/
@Service
public class SketchAnalysisServiceImpl implements SketchAnalysisService {

    @Autowired
    private SketchAnalysisRespority sketchAnalysisRespority;

    @Override
    public Commes update(SketchAnalysis sketchAnalysis) {
        try {
              SketchAnalysis sketchAnalysis1 = sketchAnalysisRespority.findBySketchTypeNameAndStudentNumber(sketchAnalysis.getSketchTypeName(),sketchAnalysis.getStudentNumber());
              if (sketchAnalysis1!=null){
                  sketchAnalysis1.setSketchTypeName(sketchAnalysis.getSketchTypeName());
                  sketchAnalysis1.setScoreCount(sketchAnalysis.getScoreCount());
                  return Commes.success(sketchAnalysisRespority.save(sketchAnalysis1));
              }else {
                  return Commes.success(sketchAnalysisRespority.save(sketchAnalysis));
              }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","更新失败");
        }
    }


    @Override
    public Commes getPersonal(Long studentNumber) {
        try {
            List<SketchAnalysis> list = sketchAnalysisRespority.findByStudentNumber(studentNumber);
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("402","查询失败");
        }
    }
}

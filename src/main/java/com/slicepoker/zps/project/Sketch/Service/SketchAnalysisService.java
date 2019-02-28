package com.slicepoker.zps.project.Sketch.Service;

import com.slicepoker.zps.project.Sketch.Pojo.SketchAnalysis;
import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2019/2/26 16:45
 **/
public interface SketchAnalysisService {

   Commes update(SketchAnalysis sketchAnalysis);

   Commes getPersonal(Long studentNumber);

}

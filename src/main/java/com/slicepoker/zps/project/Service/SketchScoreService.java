package com.slicepoker.zps.project.Service;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Pojo.SketchScore;

/**
 * @author Zps
 * @date 2018/11/2 15:12
 **/
public interface SketchScoreService {

    Commes addSketch(SketchScore sketchScore);

    Commes deleteSketch(String type);

    Commes findFuzzy();


}

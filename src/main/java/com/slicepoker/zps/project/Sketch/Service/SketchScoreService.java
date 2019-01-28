package com.slicepoker.zps.project.Sketch.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2018/11/2 15:12
 **/
public interface SketchScoreService {

    Commes addSketch(SketchScore sketchScore);

    Commes deleteSketch(Long id);

    Commes findFuzzy();

    Commes findTypeList();

    Commes findfuzzy(SketchScore sketchScore ,Pageable pageable);

}

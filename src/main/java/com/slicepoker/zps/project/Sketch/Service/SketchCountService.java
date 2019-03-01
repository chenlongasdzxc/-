package com.slicepoker.zps.project.Sketch.Service;

import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2019/3/1 13:53
 **/
public interface SketchCountService {

    Commes undateCount(Sketch sketch);

    Commes getCountData(Pageable pageable);
}

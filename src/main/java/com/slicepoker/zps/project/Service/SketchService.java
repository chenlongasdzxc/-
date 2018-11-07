package com.slicepoker.zps.project.Service;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Pojo.Sketch;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/2 14:08
 **/
public interface SketchService {

    Commes updateSketch(Sketch sketch);

    Commes findSketch(Long studentNumber, Date createDateStart, Date createDateStop, Pageable pageable);

    Commes findByClass(String className);

    Commes setStates(Long id,String states);
}
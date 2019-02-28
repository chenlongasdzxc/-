package com.slicepoker.zps.project.Sketch.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import org.springframework.data.domain.Pageable;

import java.util.Date;

/**
 * @author Zps
 * @date 2018/11/2 14:08
 **/
public interface SketchService {

    Commes updateSketch(Sketch sketch);

    Commes findFuzzy(Sketch sketch, Pageable pageable);

    Commes findByClass(String className);

    Commes setStates(Sketch sketch);

    Commes findAll();

    Commes findByStudentNumber(Long studentNumber,Pageable pageable);

    Commes findByStates();

    Commes delete(Long id);

}

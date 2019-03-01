package com.slicepoker.zps.project.Sketch.Respority;

import com.slicepoker.zps.project.Sketch.Pojo.SketchCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author Zps
 * @date 2019/3/1 13:56
 **/
public interface SketchCountRespority extends JpaRepository<SketchCount,Long>, JpaSpecificationExecutor<SketchCount> {

    SketchCount findByStudentNumber(Long studentNumber);
}

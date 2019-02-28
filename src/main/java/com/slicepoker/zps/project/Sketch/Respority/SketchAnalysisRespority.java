package com.slicepoker.zps.project.Sketch.Respority;

import com.slicepoker.zps.project.Sketch.Pojo.SketchAnalysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2019/2/26 16:44
 **/
public interface SketchAnalysisRespority extends JpaRepository<SketchAnalysis,Long>, JpaSpecificationExecutor<SketchAnalysis> {

    SketchAnalysis findBySketchTypeNameAndStudentNumber(String SketchTypeName,Long studentNumber);

    List findByStudentNumber(Long studentNumber);
}

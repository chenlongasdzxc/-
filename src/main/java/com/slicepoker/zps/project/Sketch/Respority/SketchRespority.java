package com.slicepoker.zps.project.Sketch.Respority;

import com.slicepoker.zps.project.Sketch.Pojo.Sketch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/2 14:07
 **/
public interface SketchRespority extends JpaRepository<Sketch,Long>, JpaSpecificationExecutor<Sketch> {

    /*List findByClassNameAndDeletedIsFalse(String className);*/

    /*Sketch findByStudentNumberAndDeletedIsFalse(Long studentNumber);*/

    Sketch findByIdAndDeletedIsFalse(Long id);

    Sketch findByIdAndDeletedIsFalseAndSketchStatesIsFalse(Long id);

    List findByStudentNumberAndDeletedIsFalse(Long studentNumber);

    List findBySketchStatesIsFalse();

    List findByStudentClassAndDeletedIsFalseAndAndSketchStatesIsTrue(String studentClass);

    @Query(value="select sum(sketchScore) from Sketch where studentNumber = ?1")
    double sumSketchScore(Long studentNumber);
}

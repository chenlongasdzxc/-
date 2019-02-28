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


    Sketch findByIdAndDeletedIsFalse(Long id);

    Sketch findByIdAndDeletedIsFalseAndSketchStatesIsFalse(Long id);

    List findBySketchStatesIsFalse();

    List findByStudentClassAndDeletedIsFalseAndAndSketchStatesIsTrue(String studentClass);

    @Query(value="select sum(sketchScore) from Sketch where studentNumber = ?1 and type =?2 and deleted = false and sketchStates =?3")
    Double sumSketchScore(Long studentNumber,String type,String sketchStates);

}

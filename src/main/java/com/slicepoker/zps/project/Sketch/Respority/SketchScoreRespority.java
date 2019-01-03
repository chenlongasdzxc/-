package com.slicepoker.zps.project.Sketch.Respority;

import com.slicepoker.zps.project.Sketch.Pojo.SketchScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/2 15:09
 **/
public interface SketchScoreRespority extends JpaRepository<SketchScore,Long> {

    SketchScore findByTypeAndDeletedIsFalse(String type);

    @Query(value="select type from SketchScore")
    List<SketchScore> findType();


}

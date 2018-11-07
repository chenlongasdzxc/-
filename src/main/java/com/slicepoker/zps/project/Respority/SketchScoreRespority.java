package com.slicepoker.zps.project.Respority;

import com.slicepoker.zps.project.Pojo.SketchScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/2 15:09
 **/
public interface SketchScoreRespority extends JpaRepository<SketchScore,Long> {

    SketchScore findByTypeAndDeletedIsFalse(String type);


}

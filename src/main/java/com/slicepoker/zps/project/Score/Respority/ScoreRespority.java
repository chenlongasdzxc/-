package com.slicepoker.zps.project.Score.Respority;

import com.slicepoker.zps.project.Score.Pojo.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/22 14:33
 **/
public interface ScoreRespority extends JpaRepository<Score,Long>, JpaSpecificationExecutor<Score> {

    List findByStudentNumber(Long studentNumber);
}

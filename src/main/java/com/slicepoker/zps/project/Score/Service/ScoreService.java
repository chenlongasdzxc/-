package com.slicepoker.zps.project.Score.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2018/11/22 14:45
 **/
public interface ScoreService {

    Commes findAllScore();

    Commes findByStuCode(Long studentCode);
}

package com.slicepoker.zps.project.Score.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.Score.Pojo.Score;
import com.slicepoker.zps.project.Score.Respority.ScoreRespority;
import com.slicepoker.zps.project.Score.Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/22 14:45
 **/
@Service
public class ScoreServiceImpl implements ScoreService {


    @Autowired
    ScoreRespority scoreRespority;


    /**
     * 查询所有成绩
     * **/
    @Override
    public Commes findAllScore() {
        try {
            List<Score> list = scoreRespority.findAll();
            return Commes.success(list);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }


    /**
     * 通过学号查询成绩
     * @param studentCode
     * **/
    @Override
    public Commes findByStuCode(Long studentCode) {
        try {
            List<Score> list = scoreRespority.findByStudentCode(studentCode);
            if (list.size()>0){
            return Commes.success(list);
            }else {
                return Commes.errorMes("401","暂无数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }

    }
}

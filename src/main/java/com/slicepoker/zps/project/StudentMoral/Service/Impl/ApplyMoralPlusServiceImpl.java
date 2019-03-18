package com.slicepoker.zps.project.StudentMoral.Service.Impl;

import com.slicepoker.zps.project.StudentMoral.Pojo.StudentMoralPlus;
import com.slicepoker.zps.project.StudentMoral.Respority.StudentMoralPlusRespority;
import com.slicepoker.zps.project.StudentMoral.Service.ApplyMoralPlusService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zps
 * @date 2019/3/17 21:55
 **/
@Service
public class ApplyMoralPlusServiceImpl implements ApplyMoralPlusService {

    @Autowired
    private StudentMoralPlusRespority plusRespority;

    @Override
    public Commes applyMoral(StudentMoralPlus studentMoralPlus) {
        try {
            return Commes.successMes();
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("","");
        }
    }
}

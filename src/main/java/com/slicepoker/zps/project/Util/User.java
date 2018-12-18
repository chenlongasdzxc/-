package com.slicepoker.zps.project.Util;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Respority.UserRespority;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Zps
 * @date 2018/12/18 17:39
 **/
public class User {

    @Autowired
    private UserRespority userRespority;

    public void setUser(List list){
        if (!list.isEmpty()){


        }
    }
}

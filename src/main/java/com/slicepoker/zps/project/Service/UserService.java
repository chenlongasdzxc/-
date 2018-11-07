package com.slicepoker.zps.project.Service;

import com.slicepoker.zps.project.Pojo.Commes;

/**
 * @author Zps
 * @date 2018/10/12 17:42
 **/
public interface UserService {


    Commes changePassword(Long studentNumber,String userPassword);

    Commes setGradeLevels(Long gradeLevels,Long studentNumber);


}

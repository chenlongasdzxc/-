package com.slicepoker.zps.project.User.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.User;

/**
 * @author Zps
 * @date 2018/10/12 17:42
 **/
public interface UserService {


    Commes changePassword(Long id,String userPassword);

    Commes setGradeLevels(Long gradeLevels,Long studentNumber);

    Commes undateUser(User user);

    Commes findId(String userName);

}

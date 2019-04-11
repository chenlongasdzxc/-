package com.slicepoker.zps.project.User.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.User;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2018/10/12 17:42
 **/
public interface UserService {


    Commes changePassword(Long id,String userPassword);

    Commes updateUser(User user);

    Commes findId(String userName);

    Commes findFuzzy(User user, Pageable pageable);

    Commes addUser(User user);

    Commes deleteUser(Long id);

}

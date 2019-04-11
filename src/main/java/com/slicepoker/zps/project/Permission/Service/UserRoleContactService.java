package com.slicepoker.zps.project.Permission.Service;

import com.slicepoker.zps.project.Permission.Pojo.UserRoleContact;
import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2019/4/4 15:14
 **/
public interface UserRoleContactService {

    Commes add(Long studentNumber, String userName ,String roleCode);

    Commes delete(Long id);

    Commes findByStudentNumber(Long studentNumber,String userName);
}

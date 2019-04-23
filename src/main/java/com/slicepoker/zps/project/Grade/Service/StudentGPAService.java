package com.slicepoker.zps.project.Grade.Service;

import com.slicepoker.zps.project.Grade.Pojo.StudentGPA;
import com.slicepoker.zps.project.User.Pojo.Commes;

/**
 * @author Zps
 * @date 2019/4/23 14:36
 **/
public interface StudentGPAService {

    Commes update(StudentGPA studentGPA);

    Commes findAll();
}

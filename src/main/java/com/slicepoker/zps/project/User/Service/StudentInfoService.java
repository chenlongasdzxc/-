package com.slicepoker.zps.project.User.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2018/10/26 9:52
 **/
public interface StudentInfoService {

    Commes findFuzzy(Long studentCode, String studentName, Integer sex, String studentClass,String roomNumber, Pageable pageable);

    /*Commes findRoom(Pageable pageable);*/

    Commes updateInfo(StudentInformation studentInformation);

    Commes setFileCard(Long studentNumber);

    Commes findByStudentClass(String studentClass);

    Commes findStudentByStudentNumber(String userName);

}

package com.slicepoker.zps.project.User.Service;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import org.springframework.data.domain.Pageable;

/**
 * @author Zps
 * @date 2018/10/26 9:52
 **/
public interface StudentInfoService {

    Commes findFuzzy(StudentInformation studentInformation,Pageable pageable);

    Commes updateInfo(StudentInformation studentInformation);

    Commes findStudentByStudentNumber(String userName);

    Commes findFileCard();

    Commes getStudentInfoExcel();

    Commes findStudentInformation(Long studentNumber);

    Commes findStudentList(StudentInformation studentInformation);
}

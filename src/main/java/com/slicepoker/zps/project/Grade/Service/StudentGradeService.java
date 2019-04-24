package com.slicepoker.zps.project.Grade.Service;

import com.slicepoker.zps.project.Grade.Pojo.StudentGrade;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Zps
 * @date 2019/4/22 15:44
 **/
public interface StudentGradeService {

    void exportStudentGradeExcel(HttpServletResponse response);

    Commes importExcel(MultipartFile file);

    Commes findFuzzy(StudentGrade studentGrade, Pageable pageable);

    Commes update(StudentGrade studentGrade);

    Commes findGradeNameList(String year,String major);

    Commes updateStates(Long id,String states);


}

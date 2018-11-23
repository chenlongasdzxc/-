package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import com.slicepoker.zps.project.User.Respority.StudentInfoRespority;
import com.slicepoker.zps.project.User.Service.StudentInfoService;
import com.slicepoker.zps.project.Util.CodeToGrade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zps
 * @date 2018/10/26 9:53
 **/
@Service
public class StudentInfoServiceImpl implements StudentInfoService {

    @Autowired
    StudentInfoRespority studentInfoRespority;

    @Autowired
    CodeToGrade codeToGrade;

    /**
    * 模糊查询
     * @param studentCode
     * @param studentName
     * @param sex
     * @param roomNumber
     * @param studentClass
    * */
    @Override
    public Commes findFuzzy(Long studentCode, String studentName, Integer sex,
                            String studentClass,
                            String roomNumber,
                            Pageable pageable) {
        try{
            Page<StudentInformation> page = studentInfoRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                //学号
                if (studentCode!=null){
                    list.add(cb.equal(root.get("studentCode"),studentCode));
                }
                //姓名
                if(studentName!=null){
                    list.add(cb.equal(root.get("studentName"),studentName));
                }
                //班级
                if (studentClass!=null){
                    list.add(cb.equal(root.get("studentClass"),studentClass));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("500","参数错误");
        }

    }

    /*@Override
    public Commes findRoom(Pageable pageable) {
        try {
            List list =studentInfoRespority.findroomNumber();
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }*/

    /**
     * 更新学生信息
     * @param studentInformation
     * **/
    @Override
    public Commes updateInfo(StudentInformation studentInformation) {
        try {
            StudentInformation studentInformation1 = studentInfoRespority.findByStudentCode(studentInformation.getStudentCode());
            if (studentInformation1==null){
                Long grade = codeToGrade.codeToGrade(studentInformation.getStudentCode());
                studentInformation.setStudentClass(codeToGrade.codeToClass(studentInformation.getStudentCode()));
                studentInformation.setGrade(grade);
                return Commes.success(studentInfoRespority.save(studentInformation));
            }else {
                return Commes.errorMes("401","已存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","失败");
        }
    }
}

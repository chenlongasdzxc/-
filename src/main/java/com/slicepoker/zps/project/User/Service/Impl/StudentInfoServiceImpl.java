package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import com.slicepoker.zps.project.User.Respority.StudentInfoRespority;
import com.slicepoker.zps.project.User.Respority.UserRespority;
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

    @Autowired
    UserRespority userRespority;

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


    /**
     * 更新学生信息
     * @param studentInformation
     * **/
    @Override
    public Commes updateInfo(StudentInformation studentInformation) {
        try {
                if(studentInformation.getId()!=null){
                    return Commes.success(studentInfoRespority.save(studentInformation));
                }else {
                    Long grade=codeToGrade.codeToGrade(studentInformation.getStudentNumber());
                    studentInformation.setStudentClass(codeToGrade.codeToClass(studentInformation.getStudentNumber()));
                    studentInformation.setGrade(grade);
                    return Commes.success(studentInfoRespority.save(studentInformation));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Commes.errorMes("405", "失败");
        }
    }

    /**
     * 设置建档立卡
     * @param studentNumber
     * **/
    @Override
    public Commes setFileCard(Long studentNumber) {
        try {
            StudentInformation studentInformation = studentInfoRespority.findByStudentNumber(studentNumber);
            if (studentInformation!=null){
                if (!studentInformation.isFileCard()){
                    studentInformation.setFileCard(true);
                    return Commes.success(studentInfoRespority.save(studentInformation));
                }else {
                    return Commes.errorMes("401","已是建档立卡");
                }
            }else {
                return Commes.errorMes("405","学号不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","设置失败");
        }
    }

    /**
     * @param studentClass
     * **/
    @Override
    public Commes findByStudentClass(String studentClass) {
        try {
            List<StudentInformation> list = studentInfoRespority.findByStudentClass(studentClass);
            if (!list.isEmpty()){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查询失败");
        }
    }

    /**
     * @param userName
     * 根据学号查找该实体
     * @return studentInformation
     * **/
    @Override
    public Commes findStudentByStudentNumber(String userName) {
        try {
            Long studentNumber = userRespority.findNumber(userName,false);
            StudentInformation studentInformation = studentInfoRespority.findByStudentNumber(studentNumber);
            if (studentInformation!=null){
                return Commes.success(studentInformation);
            }else {
                return Commes.errorMes("401","数据为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查找失败");
        }
    }
}

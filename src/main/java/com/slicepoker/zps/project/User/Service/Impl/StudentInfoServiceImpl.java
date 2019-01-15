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
     * @param studentInformation
    * */
    @Override
    public Commes findFuzzy(StudentInformation studentInformation) {
        try{
            List<StudentInformation> list1 = studentInfoRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                    if (studentInformation.getFindWord() != null && !"".equals(studentInformation.getFindWord())) {
                            if (studentInformation.getFindWord().length()==12){
                            Long findWord=Long.parseLong(studentInformation.getFindWord());
                            list.add(cb.or(cb.equal(root.get("studentNumber"), findWord)));
                            }else {
                                list.add(
                                        cb.or(
                                                cb.equal(root.get("studentName"),studentInformation.getFindWord()),
                                                cb.equal(root.get("studentClass"),studentInformation.getFindWord()),
                                                cb.equal(root.get("idCard"),studentInformation.getFindWord())
                                        )
                                );
                            }
                    }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }));
            return Commes.success(list1);
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
                if(studentInformation.getId()!=null&&studentInformation!=null){
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

    /**
     * @description 查找建档立卡
     * **/
    @Override
    public Commes findFileCard() {
        try {
            List<StudentInformation> list = studentInfoRespority.findByFileCard(true);
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("401","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","查找失败");
        }
    }
}

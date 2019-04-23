package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.Student;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import com.slicepoker.zps.project.User.Respority.StudentInfoRespority;
import com.slicepoker.zps.project.User.Respority.UserRespority;
import com.slicepoker.zps.project.User.Service.StudentInfoService;
import com.slicepoker.zps.project.Util.BaseEntily;
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
    public Commes findFuzzy(StudentInformation studentInformation,Pageable pageable) {
        try{
            Page<StudentInformation> page = studentInfoRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                    if (studentInformation.getFindWord() != null && !"".equals(studentInformation.getFindWord())) {
                        if (studentInformation.getFindWord().length()==12){
                            Long findWord = Long.parseLong(studentInformation.getFindWord());
                            list.add(cb.or(cb.equal(root.get("studentNumber"),findWord)));
                        }else {
                            list.add(
                                    cb.or(

                                            cb.equal(root.get("studentName"), studentInformation.getFindWord()),
                                            cb.like(root.get("studentClass"), "%" + studentInformation.getFindWord() + "%"),
                                            cb.equal(root.get("idCard"), studentInformation.getFindWord()),
                                            cb.like(root.get("roomNumber"), "%" + studentInformation.getFindWord() + "%")
                                    )
                            );
                        }
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
     * @param userName
     * 根据学号查找所在班级
     * @return studentInformation
     * **/
    @Override
    public Commes findStudentByStudentNumber(String userName) {
        try {
            Long studentNumber = userRespority.findNumber(userName,false);
            StudentInformation studentInformation = studentInfoRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (studentInformation!=null){
                return Commes.success(studentInformation.getStudentClass());
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


    @Override
    public Commes getStudentInfoExcel() {
        return null;
    }

    /**
     * @param studentNumber
     * @description 通过学号查找该实体信息
     * **/
    @Override
    public Commes findStudentInformation(Long studentNumber) {
        try {
            StudentInformation studentInformation = studentInfoRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (studentInformation!=null){
                return Commes.success(studentInformation);
            }else {
                return Commes.errorMes("402","该实体已删除");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查找失败");
        }
    }

    /**
     * @param studentInformation
     * @description 查找学生list
     * **/
    @Override
    public Commes findStudentList(StudentInformation studentInformation) {
        try {
            List<StudentInformation> list = studentInfoRespority.findByGradeAndStudentClassAndDeletedIsFalse(
                    studentInformation.getGrade(),
                    studentInformation.getStudentClass()
            );
            List list1 = setStudentData(list);
            if (list1.size()>0){
                return Commes.success(list1);
            } else {
                return Commes.errorMes("402","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }


    private List setStudentData(List<StudentInformation> list){
        List<Student> list1= new ArrayList<>();
        if (list.size()>0){
            for (StudentInformation studentInformation:list) {
                Student student = new Student();
                student.setGrade(studentInformation.getGrade());
                student.setMajor(studentInformation.getMajor());
                student.setStudentClass(studentInformation.getStudentClass());
                student.setStudentName(studentInformation.getStudentName());
                student.setStudentClass(studentInformation.getStudentClass());
                student.setStudentNumber(studentInformation.getStudentNumber());
                list1.add(student);
            }
        }
        return list1;
    }


    @Override
    public Commes findMajorList() {
        try {
            List list = studentInfoRespority.findMajorList();
            if (list.size()>0){
                return Commes.success(list);
            }else {
                return Commes.errorMes("201","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}

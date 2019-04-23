package com.slicepoker.zps.project.Grade.Service.Impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.slicepoker.zps.project.Grade.Pojo.StudentGrade;
import com.slicepoker.zps.project.Grade.Respority.StudentGradeRespority;
import com.slicepoker.zps.project.Grade.Service.StudentGradeService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.Predicate;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Zps
 * @date 2019/4/22 15:44
 **/
@Service
public class StudentGradeServiceImpl implements StudentGradeService {


    @Autowired
    private StudentGradeRespority studentGradeRespority;

    /**
     * @description 导出模板
     * **/
    public void exportStudentGradeExcel(HttpServletResponse response){
        try {
            List list = new ArrayList();
            Workbook workbook =ExcelExportUtil.exportExcel(new ExportParams("成绩表","表1"),StudentGrade.class,list);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            String fileName = "成绩信息导入模板.xls";
            response.reset();
            response.setContentType("application/msexcel;charset=utf-8");
            response.setHeader("Content-disposition", "attachment;filename= "+ URLEncoder.encode(fileName));
            response.getOutputStream().write(bytes);
            response.getOutputStream().flush();
            response.getOutputStream().close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public Commes importExcel(MultipartFile file){
        if (file == null ){
            return Commes.badRequestError();
        }else {
            try {
                ImportParams importParams = new ImportParams();
                importParams.setHeadRows(1);
                importParams.setTitleRows(1);
                List<StudentGrade> list =ExcelImportUtil.importExcel(file.getInputStream(), StudentGrade.class,importParams);
                for (StudentGrade studentGrade:list){
                    StudentGrade studentGrade1 = studentGradeRespority.findByStudentNumberAndGradeNameAndGradeYear(studentGrade.getStudentNumber(),studentGrade.getGradeName(),studentGrade.getGradeYear());
                    if (studentGrade1!=null){
                        studentGrade.setId(studentGrade1.getId());
                        studentGrade.setStates("GS001");
                    }
                    studentGrade.setStates("GS001");
                    studentGradeRespority.save(studentGrade);
                }
                return Commes.successMes();
            }catch (Exception e){
                e.printStackTrace();
                return Commes.badRequestError();
            }
        }
    }

    /**
     * @param pageable
     * @param studentGrade
     * @description 模糊查询
     * ***/
    @Override
    public Commes findFuzzy(StudentGrade studentGrade, Pageable pageable) {
        try {
            Page<StudentGrade> page = studentGradeRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (studentGrade.getGradeYear()!=null && !"".equals(studentGrade.getGradeYear())){
                    list.add(cb.equal(root.get("gradeYear"),studentGrade.getGradeYear()));
                }
                if (studentGrade.getStudentClass()!=null && !"".equals(studentGrade.getStudentClass())){
                    list.add(cb.equal(root.get("studentClass"),studentGrade.getStudentClass()));
                }
                if (studentGrade.getMajor()!=null && !"".equals(studentGrade.getMajor())){
                    list.add(cb.equal(root.get("major"),studentGrade.getMajor()));
                }
                if (studentGrade.getGrade()!=null && !"".equals(studentGrade.getGrade())){
                    list.add(cb.equal(root.get("grade"),studentGrade.getGrade()));
                }
                if (studentGrade.getStudentNumber()!=null && !"".equals(studentGrade.getStudentNumber())){
                    list.add(cb.equal(root.get("studentNumber"),studentGrade.getStudentNumber()));
                }
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }


    /**
     * @param studentGrade
     * @description 更新
     * **/
    @Override
    public Commes update(StudentGrade studentGrade) {
        try {
            StudentGrade studentGrade1 = studentGradeRespority.findByIdAndDeletedIsFalse(studentGrade.getId());
            if (studentGrade1!=null){
                studentGrade1.setStates(studentGrade.getStates());
                studentGrade1.setGradeScore(studentGrade.getGradeScore());
                studentGradeRespority.save(studentGrade1);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","没有该实体");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }



    @Override
    public Commes findGradeNameList(String year, String major) {
        try {
            List list = studentGradeRespority.findMajorGradeNameList(year, major);
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

package com.slicepoker.zps.project.Util;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.slicepoker.zps.project.Student.Service.StudentMoralService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.StudentInformation;
import com.slicepoker.zps.project.User.Respority.StudentInfoRespority;
import com.slicepoker.zps.project.User.Service.Impl.UserServiceImpl;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Zps
 * @date 2018/12/19 9:35
 **/
@RestController
public class ExcelUtil {


    @Autowired
    private StudentInfoRespority studentInfoRespority;

    @Autowired
    private CodeToGrade codeToGrade;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private StudentMoralService studentMoralService;

    /**
     * @param response
     * 生成学生信息导入模板
     * **/
    @GetMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response)throws IOException {
        List list = new ArrayList();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("信息表","表1"),StudentInformation.class,list);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        workbook.write(os);
        byte[] bytes = os.toByteArray();
        os.close();
        String fileName = "学生信息导入模板.xls";
        response.reset();
        response.setContentType("application/msexcel;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename= "+ URLEncoder.encode(fileName));
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }


    /**
     * @param file
     * 导入学生信息数据
     * **/
    @PostMapping("/importExcel")
    public Commes importExcel(MultipartFile file){
        if (file == null){
            return Commes.badRequestError();
        }else {
            try {
                ImportParams importParams = new ImportParams();
                importParams.setHeadRows(1);
                importParams.setTitleRows(1);
                List<StudentInformation> list =ExcelImportUtil.importExcel(file.getInputStream(),StudentInformation.class,importParams);
                for (StudentInformation studentInformation: list){
                    StudentInformation studentInformation1 = studentInfoRespority.findByStudentNumberAndDeletedIsFalse(studentInformation.getStudentNumber());
                    if (studentInformation1!=null){
                        studentInformation.setId(studentInformation1.getId());
                        studentInformation.setGrade(codeToGrade.codeToGrade(studentInformation1.getStudentNumber()));
                        studentInformation.setMajor(codeToGrade.codeToClass(studentInformation1.getStudentNumber()));
                        userService.setUser(studentInformation.getStudentNumber());
                        studentMoralService.setStudentMoral(studentInformation.getStudentNumber(),studentInformation.getStudentName());
                    } studentInfoRespority.saveAndFlush(studentInformation);
                }
            }catch (Exception e){
                e.printStackTrace();
                return Commes.errorMes("401","导入失败");
            }
            return Commes.successMes();
        }
    }
}

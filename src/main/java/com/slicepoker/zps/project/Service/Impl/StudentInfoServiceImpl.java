package com.slicepoker.zps.project.Service.Impl;

import com.slicepoker.zps.project.Pojo.Commes;
import com.slicepoker.zps.project.Pojo.StudentInformation;
import com.slicepoker.zps.project.Respority.StudentInfoRespority;
import com.slicepoker.zps.project.Service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    /*
    * 模糊查询
    * */
    @Override
    public Commes findFuzzy(Long studentCode, String studentName, Integer sex, String studentClass, Pageable pageable) {
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
}

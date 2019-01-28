package com.slicepoker.zps.project.Checking.Service.Impl;

import com.slicepoker.zps.project.Checking.Pojo.StudentChecking;
import com.slicepoker.zps.project.Checking.Respority.StudentCheckingRespority;
import com.slicepoker.zps.project.Checking.Service.StudentCheckingService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Zps
 * @date 2019/1/22 17:14
 **/
@Service
public class StudentCheckingServiceImpl implements StudentCheckingService {

   @Autowired
    private StudentCheckingRespority studentCheckingRespority;

    @Override
    public Commes update(StudentChecking studentChecking) {
        try {
            if (studentChecking!=null && !"".equals(studentChecking)){
                studentChecking.setUpdateDate(new Date());
                return Commes.success(studentCheckingRespority.save(studentChecking));
            }else {
                return Commes.errorMes("402","保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","error");
        }
    }


    @Override
    public Commes get(Pageable pageable) {
        try {
            Page<StudentChecking> page = studentCheckingRespority.findByDeletedIsFalse(pageable);
            if (page.getSize()>0){
                return Commes.success(page);
            }else {
                return Commes.errorMes("402","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查找失败");
        }
    }
}

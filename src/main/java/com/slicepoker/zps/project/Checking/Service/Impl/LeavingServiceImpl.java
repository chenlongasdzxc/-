package com.slicepoker.zps.project.Checking.Service.Impl;

import com.slicepoker.zps.project.Checking.Pojo.AskForLeave;
import com.slicepoker.zps.project.Checking.Respority.LeavingRespority;
import com.slicepoker.zps.project.Checking.Service.LeavingService;
import com.slicepoker.zps.project.User.Pojo.Commes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Zps
 * @date 2019/2/15 17:34
 **/
@Service
public class LeavingServiceImpl implements LeavingService {

    @Autowired
    private LeavingRespority leavingRespority;

    /**
     * @param askForLeave
     * @description 新增或编辑
     * **/
    @Override
    public Commes update(AskForLeave askForLeave) {
        try {
            if (askForLeave!=null && !"".equals(askForLeave)){
                askForLeave.setUpDate(new Date());
                if (askForLeave.getStates()==null){
                    askForLeave.setStates("DO001");
                    return Commes.success(leavingRespority.save(askForLeave));
                }else {
                    return Commes.success(leavingRespority.save(askForLeave));
                }
            }else {
                return Commes.errorMes("402","数据为NULL，保存失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","更新失败");
        }
    }


    /**
     * @param askForLeave
     * @param pageable
     * @description 查找同年级，同专业请假详情
     * **/
    @Override
    public Commes findLeaving(AskForLeave askForLeave,Pageable pageable) {
        try {
            Page<AskForLeave> page = leavingRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("deleted"),false),
                                cb.equal(root.get("major"),askForLeave.getMajor()),
                                cb.equal(root.get("grade"),askForLeave.getGrade()),
                                cb.equal(root.get("states"),"DO001")
                        )
                );
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
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

    /**
     * @param pageable
     * @param askForLeave
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(AskForLeave askForLeave, Pageable pageable) {
        try {
            Page<AskForLeave> page = leavingRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(
                        cb.and(
                                cb.equal(root.get("deleted"),false),
                                cb.equal(root.get("major"),askForLeave.getMajor()),
                                cb.equal(root.get("grade"),askForLeave.getGrade()),
                                cb.equal(root.get("states"),"DO001")
                        )
                );
                list.add(
                        cb.or(
                                cb.equal(root.get("studentName"),askForLeave.getKeyWord())
                        )
                );
                return cb.and(list.toArray(new Predicate[list.size()]));
            }),pageable);
            return Commes.success(page);
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }

    /**
     * @param id
     * @description 删除请假信息
     * **/
    @Override
    public Commes delete(Long id) {
        try{
            AskForLeave askForLeave = leavingRespority.findByIdAndDeletedIsFalse(id);
            if (askForLeave!=null){
                askForLeave.setDeleted(true);
                return Commes.success(leavingRespority.save(askForLeave));
            }else {
                return Commes.errorMes("402","没有找到该条数据");
            }
        }catch(Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","删除失败");
        }
    }


    /**
     * @param pageable
     * @param studentNumber
     * @description 查找个人请假信息
     * **/
    @Override
    public Commes findPersonalData(Long studentNumber, Pageable pageable) {
        try {
            Page<AskForLeave> page = leavingRespority.findByStudentNumberAndDeletedIsFalse(studentNumber, pageable);
            if (page.getSize()>0){
                return Commes.success(page);
            }else {
                return Commes.errorMes("402","没有数据");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("401","查询失败");
        }
    }
}

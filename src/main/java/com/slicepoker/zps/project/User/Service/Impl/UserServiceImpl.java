package com.slicepoker.zps.project.User.Service.Impl;

import com.slicepoker.zps.project.User.Pojo.Commes;
import com.slicepoker.zps.project.User.Pojo.User;
import com.slicepoker.zps.project.User.Respority.StudentInfoRespority;
import com.slicepoker.zps.project.User.Respority.UserRespority;
import com.slicepoker.zps.project.User.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Zps
 * @date 2018/10/12 17:42
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRespority userRespority;

    @Autowired
    private StudentInfoRespority studentInfoRespority;

    /**
     * @param id 学号
     * @param userPassword  密码
     * 通过学号修改密码
     * **/
    @Override
    public Commes changePassword(Long id, String userPassword) {
        try {
            User user = userRespority.findByIdAndDeletedIsFalse(id);
            if (user!=null && !"".equals(userPassword)){
                if (!Objects.equals(userPassword,user.getUserPassword())){
                user.setUserPassword(userPassword);
                return Commes.success(userRespority.save(user));
                }else {
                    return Commes.errorMes("402","密码相同");
                }
            }else {
                return Commes.errorMes("401","密码为空");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","修改密码失败");
        }
    }




    /**
     * @param user
     * **/
    @Override
    public Commes updateUser(User user) {
        try {
            User user1 = userRespority.findByUserNameAndDeletedIsFalse(user.getUserName());
            if (user1==null){
                return Commes.success(userRespority.save(user));
            }else {
                return Commes.errorMes("401","账号已存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","添加失败");
        }
    }

    /**
     * @param studentNumber
     * **/
    public void setUser(Long studentNumber){
        if (studentNumber!=null){
            User user = userRespority.findByStudentNumberAndDeletedIsFalse(studentNumber);
            if (user==null){
                user = new User();
                user.setStudentNumber(studentNumber);
                user.setUserName(Long.toString(studentNumber));
                user.setUserPassword(Long.toString(studentNumber));
            }
            userRespority.save(user);
        }
    }

    /**
     * @param userName
     * 查找id
     * **/
    @Override
    public Commes findId(String userName) {
        try {
            Long id = userRespority.findId(userName);
            if (id!=null){
                return Commes.success(id);
            }else {
                return Commes.errorMes("401","查找失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.errorMes("405","error");
        }
    }

    /**
     * @param pageable
     * @param user
     * @description 模糊查询
     * **/
    @Override
    public Commes findFuzzy(User user, Pageable pageable) {
        try {
            Page<User> page = userRespority.findAll(((root, query, cb) -> {
                List<Predicate> list = new ArrayList<>();
                list.add(cb.equal(root.get("deleted"),false));
                if (user.getUserName()!=null && !"".equals(user.getUserName())){
                    list.add(cb.equal(root.get("userName"),user.getUserName()));
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
     * @param user
     * @description 添加用户
     * **/
    @Override
    public Commes addUser(User user) {
        try {
            User user1 = userRespority.findByStudentNumberAndDeletedIsFalse(user.getStudentNumber());
            if (user1==null){
                userRespority.save(user);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","学号已存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }

    /**
     * @param id
     * @description 删除
     * **/
    @Override
    public Commes deleteUser(Long id) {
        try {
            User user = userRespority.findByIdAndDeletedIsFalse(id);
            if (user!=null){
                user.setDeleted(true);
                userRespority.save(user);
                return Commes.successMes();
            }else {
                return Commes.errorMes("401","删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return Commes.badRequestError();
        }
    }
}

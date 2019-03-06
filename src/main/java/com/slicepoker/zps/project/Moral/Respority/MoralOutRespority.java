package com.slicepoker.zps.project.Moral.Respority;

import com.slicepoker.zps.project.Moral.Pojo.MoralOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/29 9:14
 **/
public interface MoralOutRespority extends JpaRepository<MoralOut,Long>, JpaSpecificationExecutor<MoralOut> {

    MoralOut findByIdAndDeletedIsFalse(Long id);

    @Query(value="select moralOutName from MoralOut where deleted = false ")
    List findAllMoralName();

    @Query(value="select moralOutType from MoralOut where deleted = false ")
    List findAllMoralType();

    @Query(value="select moralOutName from MoralOut where deleted = false and moralOutType =?1")
    List findByType(String moralOutType);

    MoralOut findByMoralOutNameAndDeletedIsFalse(String moralOutName);
}

package com.slicepoker.zps.project.Moral.Respority;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/28 14:10
 **/
public interface MoralPlusRespority extends JpaRepository<MoralPlus,Long>, JpaSpecificationExecutor<MoralPlus> {

    MoralPlus findByIdAndDeletedIsFalse(Long id);

    @Query(value="select distinct(moralPlusType) from MoralPlus where deleted = false ")
    List findMoralPlus();

    List findByDeletedIsFalse();

    List findByMoralPlusTypeAndDeletedIsFalse(String moralPlusType);

    @Query(value="select moralPlusScore from MoralPlus where  moralPlusType =?1 and moralPlusName =?2 and deleted = false ")
     Double find(String moralPlusType,String moralPlusName);
}

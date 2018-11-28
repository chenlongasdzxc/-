package com.slicepoker.zps.project.Moral.Respority;

import com.slicepoker.zps.project.Moral.Pojo.MoralPlus;
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

    @Query(value="select moralPlusName  from MoralPlus where deleted = false ")
    List findMoralPlus();
}

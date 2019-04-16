package com.slicepoker.zps.project.Moral.Respority;

import com.slicepoker.zps.project.Moral.Pojo.MoralDeduction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/28 16:05
 **/
public interface MoralDeductionRespority extends JpaRepository<MoralDeduction,Long>, JpaSpecificationExecutor<MoralDeduction> {

    @Query(value="select moralDeductionName from MoralDeduction where deleted = false ")
    List findMoralDeduction();

    @Query(value="select moralDeductionName from MoralDeduction where moralDeductionType =?1 and deleted = false")
    List findMoralDeductionByMoralDeductionType(String moralDeduction);

    MoralDeduction findByIdAndDeletedIsFalse(Long id);

    @Query(value="select moralDeductionType from MoralDeduction where deleted = false ")
    List findMoralDeductionType();

    @Query(value="select moralDeductionName from MoralDeduction where deleted = false ")
    List findMoralDeductionName();

    MoralDeduction findByMoralDeductionTypeAndDeletedIsFalse(String moralDeduction);
}

package com.slicepoker.zps.project.Moral.Respority;

import com.slicepoker.zps.project.Moral.Pojo.MoralExpression;
import javafx.scene.chart.ValueAxis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Zps
 * @date 2018/11/26 17:23
 **/
public interface MoralExpressionRespority extends JpaRepository<MoralExpression,Long>, JpaSpecificationExecutor<MoralExpression> {

    MoralExpression findByIdAndDeletedIsFalse(Long id);

    @Query(value="select distinct(moralExpressionName) from MoralExpression where deleted = false ")
    List findMoralExpressionName();

    @Query(value="select count(moralExpressionName) from MoralExpression where deleted = false ")
    Double moralExpressionNameCount();
}

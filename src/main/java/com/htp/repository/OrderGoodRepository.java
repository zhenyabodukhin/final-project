package com.htp.repository;

import com.htp.domain.OrderGood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderGoodRepository extends JpaRepository<OrderGood, Long> {

    @Query("select og from OrderGood og where og.orderId=:orderId")
    List<OrderGood> findByOrderId (@Param("orderId") Long id);
}

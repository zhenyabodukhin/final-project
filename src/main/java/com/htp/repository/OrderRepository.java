package com.htp.repository;

import com.htp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    void setOrderDone(Long id);

    List<Order> findByUserId(Long id);

    List<Order> findIsDone(boolean value);
}

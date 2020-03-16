package com.htp.repository;

import com.htp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("update Order t set t.isDone=true where t.id=:id")
    void setOrderDone(@Param("id") Long id);

    @Query("select t from Order t where t.id=:id")
    List<Order> findByUserId(@Param("id") Long id);

    @Query("select t from Order t where t.isDone=:isDone")
    List<Order> findIsDone(@Param("isDone") boolean value);

}

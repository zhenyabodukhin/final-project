package com.htp.repository;

import com.htp.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("update Order o set o.isDone=true where o.id=:id")
    void setOrderDone(@Param("id") Long id);

    @Query("select o from Order o where o.userId=:id")
    List<Order> findByUserId(@Param("id") Long id);

    @Query("select o from Order o where o.isDone=:isDone")
    List<Order> findIsDone(@Param("isDone") Boolean value);

    @Query("select o from Order o where o.addressId=:addressId")
    List<Order> findByAddressId(@Param("addressId") Long id);

    @Query("select o from Order o where o.phoneNumber=:phoneNumber")
    List<Order> findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

}

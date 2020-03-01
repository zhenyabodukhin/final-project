package com.htp.dao;

import com.htp.domain.Order;
import java.util.List;

public interface OrderRepositoryDao extends GenericDao<Order, Long> {

    void setOrderDone(Long id);

    List<Order> findByUserId(Long id);

    List<Order> findIsDone(boolean value);
}

package com.htp.repository;

import com.htp.domain.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PriceRepository extends JpaRepository<Price, Long> {

    @Query("select p from Price p where p.price=:value")
    Price findPriceByValue(@Param("value") Double value);
}

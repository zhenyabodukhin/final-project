package com.htp.repository;

import com.htp.domain.Good;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GoodRepository extends JpaRepository<Good, Long> {

    @Query("select g from Good g where g.goodName=:name")
    Good findGoodByName (@Param("name") String name);
}

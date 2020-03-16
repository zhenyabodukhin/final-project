package com.htp.repository;

import com.htp.domain.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdressRepository extends JpaRepository<Adress, Long> {

    @Query("select t from Adress t where t.street like :value")
    List<Adress> findContainsValue (@Param("value") String value);

}

package com.htp.repository;

import com.htp.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where lower(a.street) like concat('%',lower(:value),'%')")
    List<Address> findContainsValue (@Param("value") String value);

    @Query("select a from Address a where a.isPizzaAddress=:value")
    List<Address> findIsPizza(@Param("value") Boolean value);

}

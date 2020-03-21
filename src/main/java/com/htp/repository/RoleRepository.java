package com.htp.repository;

import com.htp.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.userId = :userId")
    List<Role> findByUserId(@Param("userId") Long id);
}

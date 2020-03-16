package com.htp.repository;

import com.htp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select t from User t where t.login = :login")
    User findByName (@Param("login") String name);

    @Query("select t from User t where t.login like :value")
    List<User> findContainsValue (@Param("value") String value);

    @Query("select t from User t where t.isDeleted = :isDeleted")
    List<User> findIsDeleted (@Param("isDeleted") boolean value);

    @Query("update User t set t.isDeleted=true where t.id=:id")
    void delete (@Param("id") Long id);
}

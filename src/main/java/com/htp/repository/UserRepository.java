package com.htp.repository;

import com.htp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.login = :login")
    User findByName (@Param("login") String name);

    @Query("select u from User u where u.login like :value")
    List<User> findContainsValue (@Param("value") String value);

    @Query("select u from User u where u.isDeleted = :isDeleted")
    List<User> findIsDeleted (@Param("isDeleted") boolean value);

    @Query("update User u set u.isDeleted=true where u.id=:id")
    void delete (@Param("id") Long id);
}

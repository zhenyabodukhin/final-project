package com.htp.repository;

import com.htp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName (String name);

    List<User> findContainsValue (String value);

    List<User> findIsDeleted (boolean value);

    List<Long> batchUpdate(List<User> users);
}

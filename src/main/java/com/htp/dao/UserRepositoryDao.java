package com.htp.dao;

import com.htp.domain.User;
import java.util.List;

public interface UserRepositoryDao extends GenericDao<User, Long> {

    User findByName (String name);

    List<User> findContainsValue (String value);

    List<User> findIsDeleted (boolean value);

    List<Long> batchUpdate(List<User> users);
}

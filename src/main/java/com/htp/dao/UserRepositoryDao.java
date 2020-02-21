package com.htp.dao;

import com.htp.domain.User;
import java.util.List;

public interface UserRepositoryDao extends UserRepository<User, Long> {
    List<Long> batchUpdate(List<User> users);
}

package com.htp.service.impl;


import com.htp.domain.User;
import com.htp.repository.UserRepository;
import com.htp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Transactional
    @Override
    public User findById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> findContainsValue(String value) {
        return userRepository.findContainsValue(value);
    }

    @Override
    public List<User> findIsDeleted(boolean value) {
        return userRepository.findIsDeleted(value);
    }
}

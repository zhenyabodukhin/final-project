package com.htp.service.impl;


import com.htp.domain.User;
import com.htp.exception.EntityNotFoundException;
import com.htp.repository.UserRepository;
import com.htp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Long id) {
        if(userRepository.findById(id).isPresent()) {
            userRepository.delete(id);
        } else {
            throw new EntityNotFoundException(User.class, id);
        }
    }

    @Override
    public User findById(Long id) {
        Optional<User> result = userRepository.findById(id);
        if(result.isPresent()) {
            return result.get();
        } else {
            throw new EntityNotFoundException(User.class, id);
        }
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
    public List<User> findIsDeleted(Boolean value) {
        return userRepository.findIsDeleted(value);
    }
}

package com.htp.service.impl;

import com.htp.domain.Role;
import com.htp.repository.RoleRepository;
import com.htp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("RoleServiceImpl")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Transactional
    @Override
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        roleRepository.deleteById(id);
    }

    @Transactional
    @Override
    public Role findById(Long id) {
        return roleRepository.getOne(id);
    }
}


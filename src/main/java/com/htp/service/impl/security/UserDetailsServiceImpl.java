package com.htp.service.impl.security;

import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.service.RoleService;
import com.htp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    //@Qualifier("userService")
    private UserService userService;

    @Autowired
    //@Qualifier("roleService")
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        try {
            User user = userService.findByName(userName);
            List<Role> roles = roleService.findByUserId(user.getId());
            if(user.getId() == null) {
                throw new UsernameNotFoundException(String.format("User '%s' not found", userName));
            } else {
                return new org.springframework.security.core.userdetails.User(
                        user.getLogin(),
                        user.getPassword(),
                        AuthorityUtils.commaSeparatedStringToAuthorityList(roles.get(0).getRole())
                );
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("User with this name not found");
        }
    }
}

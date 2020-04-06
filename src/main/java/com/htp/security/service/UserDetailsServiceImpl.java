package com.htp.security.service;

import com.htp.domain.Role;
import com.htp.domain.User;
import com.htp.service.RoleService;
import com.htp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    private final RoleService roleService;

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

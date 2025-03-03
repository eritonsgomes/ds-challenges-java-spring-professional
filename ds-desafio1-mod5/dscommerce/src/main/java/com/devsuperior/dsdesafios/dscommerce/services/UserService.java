package com.devsuperior.dsdesafios.dscommerce.services;

import com.devsuperior.dsdesafios.dscommerce.entities.Role;
import com.devsuperior.dsdesafios.dscommerce.entities.User;
import com.devsuperior.dsdesafios.dscommerce.projections.UserDetailsProjection;
import com.devsuperior.dsdesafios.dscommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserDetailsProjection> result = repository.searchUserAndRolesByEmail(username);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }

        User user = new User();
        user.setEmail(result.getFirst().getUsername());
        user.setPassword(result.getFirst().getPassword());

        for (UserDetailsProjection projection : result) {
            user.addRole(new Role(projection.getRoleId(), projection.getAuthority()));
        }

        return user;
    }

}

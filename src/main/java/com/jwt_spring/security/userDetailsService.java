package com.jwt_spring.security;

import com.jwt_spring.dao.IAppUser;
import com.jwt_spring.entities.AppUser;
import com.jwt_spring.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class userDetailsService implements UserDetailsService {
    @Autowired
    private IAppUser iAppUser;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = iAppUser.findByEmail(username);

        //verify if the user Exists
        if(user==null) throw new UsernameNotFoundException("L'UTILISATEUR N'EXISTE PAS ");

        //return the user of Spring
        return new UserDet(user);
    }
}

package com.jwt_spring.service;

import com.jwt_spring.dao.IAppRole;
import com.jwt_spring.dao.IAppUser;
import com.jwt_spring.entities.AppRole;
import com.jwt_spring.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private IAppUser iAppUser;

    @Autowired
    private IAppRole iAppRole;

    @Override
    public AppRole saveRole(AppRole role) {
        return iAppRole.save(role);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        String hashedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return iAppUser.save(user);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        //find the role
        AppRole role = iAppRole.findByLibelle(roleName);

        //find the user
        AppUser user = iAppUser.findByEmail(email);

        //adding the role to the user
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserById(int id) {
        return iAppUser.findById(id).orElse(null);
    }
}

package com.jwt_spring.service;

import com.jwt_spring.entities.AppRole;
import com.jwt_spring.entities.AppUser;

public interface AccountService {
    public AppRole saveRole(AppRole role);
    public AppUser saveUser(AppUser user);
    public void addRoleToUser(String email , String roleName);
    public AppUser findUserById(int id);
}

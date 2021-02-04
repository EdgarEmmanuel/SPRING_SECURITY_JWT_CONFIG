package com.jwt_spring.dao;

import com.jwt_spring.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppUser extends JpaRepository<AppUser,Integer> {
    public AppUser findByEmail(String email);
}

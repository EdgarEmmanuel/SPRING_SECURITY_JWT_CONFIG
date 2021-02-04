package com.jwt_spring.dao;

import com.jwt_spring.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppRole extends JpaRepository<AppRole,Integer> {
    public AppRole findByLibelle(String libelle);
}

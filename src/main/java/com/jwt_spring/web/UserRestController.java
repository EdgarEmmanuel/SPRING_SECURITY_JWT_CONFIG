package com.jwt_spring.web;

import com.jwt_spring.dao.IAppUser;
import com.jwt_spring.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserRestController {

    @Autowired
    private IAppUser iAppUser;

    @PostMapping(value={"/register"},consumes = {"application/json"})
    public AppUser addUser(@RequestBody AppUser  user){
        return iAppUser.save(user);
    }
    
    @GetMapping(value={"/users"})
    public  List<AppUser> getAllUsers(){
        return iAppUser.findAll();
    }
    

}

package com.revature.springskeleton.controllers;

import com.revature.springskeleton.models.SiteUser;
import com.revature.springskeleton.repositories.UserRepository;
import com.revature.springskeleton.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserRepository users;

    @PostMapping("/login")
    public SiteUser loginUser(@RequestBody SiteUser testUser) {
        SiteUser checkVs = users.findByUsername(testUser.getUsername());

        return checkVs;
    }

    @PostMapping("/register")
    public SiteUser registerUser(@RequestBody SiteUser neoUser) {
        neoUser.setPassword(PasswordUtils.encrypt(neoUser.getPassword()) );
        return this.users.save(neoUser);
    }


}

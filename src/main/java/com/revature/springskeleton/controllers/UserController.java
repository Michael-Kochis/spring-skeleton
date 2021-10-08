package com.revature.springskeleton.controllers;

import com.revature.springskeleton.models.SiteUser;
import com.revature.springskeleton.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository users;

    @GetMapping("/users")
    public List<SiteUser> findAll() {
        return this.users.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<SiteUser> getUserByID(@PathVariable(value="id") Long userID) throws Exception {
        SiteUser user = users.findById(userID)
                .orElseThrow(
                        () -> new Exception("Employee not found for ID: " + userID));
        return ResponseEntity.ok().body(user);
    }

    @PostMapping("/users")
    public SiteUser makeUser(@RequestBody SiteUser neoUser) {
        return this.users.save(neoUser);
    }
}

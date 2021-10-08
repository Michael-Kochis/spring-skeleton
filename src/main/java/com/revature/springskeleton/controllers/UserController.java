package com.revature.springskeleton.controllers;

import com.revature.springskeleton.exceptions.ResourceNotFoundException;
import com.revature.springskeleton.models.SiteUser;
import com.revature.springskeleton.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserRepository users;

    @GetMapping("/")
    public List<SiteUser> findAll() {
        return this.users.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SiteUser> getUserByID(@PathVariable(value="id") Long userID)
            throws ResourceNotFoundException {
        SiteUser user = users.findById(userID)
                .orElseThrow(
                        () -> new ResourceAccessException("Employee not found for ID: " + userID)
                );
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SiteUser> updateUser(@PathVariable(value = "id") Long userID,
        @RequestBody SiteUser user) throws ResourceNotFoundException {
        SiteUser oldUser = users.findById(userID)
                .orElseThrow(
                        () -> new ResourceNotFoundException("Employee not found for ID: " + userID)
                );
        if (user.getUsername() != null && !user.getUsername().equals(""))
            oldUser.setUsername(user.getUsername());
        if (user.getFirstName() != null && !user.getFirstName().equals(""))
            oldUser.setFirstName(user.getFirstName());
        if (user.getLastName() != null && !user.getLastName().equals(""))
            oldUser.setLastName(user.getLastName());
        if (user.getEmail() != null && !user.getEmail().equals(""))
            oldUser.setEmail(user.getEmail());

        return ResponseEntity.ok(this.users.save(oldUser));
    }

    @PostMapping("/")
    public SiteUser makeUser(@RequestBody SiteUser neoUser) {
        return this.users.save(neoUser);
    }
}

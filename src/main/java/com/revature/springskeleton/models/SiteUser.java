package com.revature.springskeleton.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
public class SiteUser {
    @Id
    @Column(name="userID")
    @Getter @Setter
    private long userID;

    @Column(name="username", unique = true)
    @Getter  @Setter
    private String username;

    @Column(name="password")
    @Getter  @Setter
    private String password;

    @Column(name="firstName")
    @Getter  @Setter
    private String firstName;

    @Column(name="lastName")
    @Getter  @Setter
    private String lastName;

    @Column(name="email")
    @Getter  @Setter
    private String email;

}

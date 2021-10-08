package com.revature.springskeleton.models;

public class LoginResponse {
    public String message;
    public SiteUser user;
    public String token;

    public LoginResponse(SiteUser model) {
        String tokenSecret = System.getenv("TOKEN_SECRET");
        this.message = "Welcome, " + model.getUsername();
        this.user = model;
        this.token = tokenSecret;
    }
}

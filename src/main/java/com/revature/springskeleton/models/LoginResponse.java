package com.revature.springskeleton.models;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

public class LoginResponse {
    public String message;
    public SiteUser user;
    public String token;

    public LoginResponse(SiteUser model) {

        this.message = "Welcome, " + model.getUsername();
        this.user = model;
        this.token = generateJWT(model);
    }

    private String generateJWT(SiteUser toEncrypt) {
        String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        String tokenSecret = System.getenv("TOKEN_SECRET");
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(tokenSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder().setId(String.valueOf(toEncrypt.getUserID()) )
                .setIssuedAt(now)
                .setSubject(toEncrypt.getUsername())
                .setIssuer("Team Dungeon Keepers")
                .signWith(signatureAlgorithm, signingKey);

        long expMilli = nowMillis + 9100000;
        Date exp = new Date(expMilli);
        builder.setExpiration(exp);

        return builder.compact();
    }

}

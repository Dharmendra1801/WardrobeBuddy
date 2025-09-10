package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.Models.Tokens;
import com.Project.WardrobeBuddy.Repositories.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    TokenRepo tokenRepo;

    public boolean tokenCheck(String token) {
        return tokenRepo.findById(token).orElse(null)!=null;
    }

    public String getToken(String username) {
        String token = username+String.valueOf(username.hashCode());
        tokenRepo.save(new Tokens(token,username));
        return token;
    }

    public void revokeAuth(String token) {
        tokenRepo.deleteById(token);
    }
}

package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.Models.Tokens;
import com.Project.WardrobeBuddy.Repositories.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TokenService {

    @Autowired
    TokenRepo tokenRepo;

    public boolean checkToken(String username) {
        return tokenCheckByRepo(convertToken(username));
    }

    private String convertToken(String username) {
        return String.valueOf((username+username.hashCode()).hashCode());
    }

    public boolean tokenCheckByRepo(String token) {
        return tokenRepo.findById(token).orElse(null)!=null;
    }

    public void revokeAuth(String token) {
        tokenRepo.deleteById(convertToken(token));
    }

    public void saveToken(String username) {
        String token = convertToken(username);
        tokenRepo.save(new Tokens(token,username));
    }
}

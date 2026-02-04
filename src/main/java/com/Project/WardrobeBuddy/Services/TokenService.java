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
        return tokenCheckByRepo(getToken(username));
    }

    public boolean tokenCheckByRepo(String token) {
        return tokenRepo.findById(token).orElse(null)!=null;
    }

    public String getToken(String username) {
        String token = String.valueOf((username+username.hashCode()).hashCode());
        tokenRepo.save(new Tokens(token,username));
        return token;
    }

    public void revokeAuth(String token) {
        tokenRepo.deleteById(token);
    }
}

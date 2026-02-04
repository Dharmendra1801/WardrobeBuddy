package com.Project.WardrobeBuddy.Services;

import org.springframework.stereotype.Service;

@Service
public class HashingService {
    public String hash(String word) {
        return String.valueOf(word.hashCode());
    }
}

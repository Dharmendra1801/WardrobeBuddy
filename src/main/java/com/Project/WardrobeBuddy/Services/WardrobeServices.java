package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.Models.Wardrobe;
import com.Project.WardrobeBuddy.Repositories.WardrobeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardrobeServices {

    @Autowired
    WardrobeRepo wardrobeRepo;

    public boolean createWardrobe(Wardrobe wardrobe) {
        if (checkWardrobe(wardrobe.getUsername(),wardrobe.getWardrobeName())) return false;
        wardrobeRepo.save(wardrobe);
        return true;
    }

    public boolean checkWardrobe(String username, String wardrobeName) {
        return wardrobeRepo.findByUsernameAndWardrobeName(username, wardrobeName).orElse(null)!=null;
    }

    public boolean deleteWardrobe(String username, String wardrobeName) {
        if (!checkWardrobe(username, wardrobeName)) return false;
        wardrobeRepo.deleteByUsernameAndWardrobeName(username, wardrobeName);
        return true;
    }

    public Wardrobe getWardrobe(String username, String wardrobeName) {
        return wardrobeRepo.findByUsernameAndWardrobeName(username, wardrobeName).orElse(null);
    }

    public List<Wardrobe> getAllWardrobe(String username) {
        return wardrobeRepo.findAllByUsername(username).orElse(null);
    }

    public int getWardrobeID(String username,String wardrobeName) {
        return wardrobeRepo.getWardrobeIDByUsernameAndWardrobeName(username,wardrobeName);
    }
}

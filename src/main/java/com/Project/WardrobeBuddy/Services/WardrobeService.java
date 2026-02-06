package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.DTOs.WardrobeDTO;
import com.Project.WardrobeBuddy.Models.User;
import com.Project.WardrobeBuddy.Models.Wardrobe;
import com.Project.WardrobeBuddy.Repositories.WardrobeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class WardrobeService {

    @Autowired
    WardrobeRepo wardrobeRepo;

    @Autowired
    UserService userService;

    public boolean createWardrobe(String username, WardrobeDTO wardrobeDTO) {
        if (checkWardrobe(username, wardrobeDTO.getWardrobeName())) return false;
        User user = userService.getUser(username);
        Wardrobe wardrobe = new Wardrobe();
        wardrobe.setWardrobeName(wardrobeDTO.getWardrobeName());
        wardrobe.setDateCreated(wardrobeDTO.getDateCreated());
        wardrobe.setNote(wardrobeDTO.getNote());
        wardrobe.setProductCount(wardrobeDTO.getNoOfProducts());
        wardrobe.setProducts(new ArrayList<>());
        wardrobe.setUser(user);
        wardrobeRepo.save(wardrobe);
        return true;
    }

    public Wardrobe getWardrobeFromRepo(String username, String wardrobeName) {
        return wardrobeRepo.findByUserUsernameAndWardrobeName(username, wardrobeName).orElse(null);
    }

    public boolean checkWardrobe(String username, String wardrobeName) {
        return getWardrobeFromRepo(username, wardrobeName)!=null;
    }

    public boolean deleteWardrobe(String username, String wardrobeName) {
        if (!checkWardrobe(username, wardrobeName)) return false;
        wardrobeRepo.deleteByUserUsernameAndWardrobeName(username, wardrobeName);
        return true;
    }

    public WardrobeDTO getWardrobe(String username, String wardrobeName) {
        Wardrobe ward = getWardrobeFromRepo(username, wardrobeName);
        return convertOBJtoDTO(ward);
    }


    public Long getId(String username, String wardrobeName) {
        Wardrobe ward = getWardrobeFromRepo(username, wardrobeName);
        if (ward==null) return null;
        return ward.getId();
    }

    public List<WardrobeDTO> getAllWardrobe(String username) {
        List<Wardrobe> list = wardrobeRepo.findAllByUserUsername(username).orElse(null);
        if (list==null) return new ArrayList<>();

        List<WardrobeDTO> returnList = new ArrayList<>();

        for (Wardrobe ward: list) {
            returnList.add(convertOBJtoDTO(ward));
        }
        return returnList;
    }

    public WardrobeDTO getWardrobeById(Long id) {
        Wardrobe ward = wardrobeRepo.findById(id).orElse(null);
        return convertOBJtoDTO(ward);
    }

    private WardrobeDTO convertOBJtoDTO(Wardrobe ward) {
        if (ward==null) return null;
        WardrobeDTO wardrobe = new WardrobeDTO();
        wardrobe.setDateCreated(ward.getDateCreated());
        wardrobe.setWardrobeName(ward.getWardrobeName());
        wardrobe.setNote(ward.getNote());
        wardrobe.setNoOfProducts(ward.getProductCount());
        return wardrobe;
    }

    public boolean changeName(String username, String wardrobeName, String newName) {
        Wardrobe wardrobe = getWardrobeFromRepo(username, wardrobeName);
        if (wardrobe==null) return false;
        wardrobe.setWardrobeName(newName);
        return true;
    }

    public boolean changeNote(String username, String wardrobeName, String newNote) {
        Wardrobe wardrobe = getWardrobeFromRepo(username, wardrobeName);
        if (wardrobe==null) return false;
        wardrobe.setNote(newNote);
        return true;
    }
}

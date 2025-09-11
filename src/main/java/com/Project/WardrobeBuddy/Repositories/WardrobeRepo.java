package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.Wardrobe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardrobeRepo extends JpaRepository<Wardrobe,Integer> {
    Optional<Wardrobe> findByUsernameAndWardrobeName(String username, String wardrobeName);
    Optional<List<Wardrobe>> findAllByUsername(String username);
    @Transactional
    void deleteByUsernameAndWardrobeName(String username, String wardrobeName);
}

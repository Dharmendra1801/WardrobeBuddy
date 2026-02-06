package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.Wardrobe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface WardrobeRepo extends JpaRepository<Wardrobe,Long> {
    Optional<Wardrobe> findByUserUsernameAndWardrobeName(String username, String wardrobeName);
    Optional<List<Wardrobe>> findAllByUserUsername(String username);
    @Transactional
    void deleteByUserUsernameAndWardrobeName(String username, String wardrobeName);
}

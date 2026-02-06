package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Optional<List<Product>> findAllByWardrobeUserUsernameAndWardrobeWardrobeName(String username, String wardrobeName);
    Optional<List<Product>> findAllByWardrobeUserUsernameAndWardrobeWardrobeNameAndCategory(String username, String wardrobeName, String category);
}

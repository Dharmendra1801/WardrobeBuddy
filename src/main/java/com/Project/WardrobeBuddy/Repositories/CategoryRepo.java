package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepo extends JpaRepository<Category, String> {
    Optional<List<Category>> findAllByUserUsername(String username);
}

package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.ProductType.Accessory;
import com.Project.WardrobeBuddy.Models.ProductType.Clothe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClotheRepo extends JpaRepository<Clothe,Integer> {
    List<Clothe> findAllByWardrobeID(int wardrobeID);
}

package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.ProductType.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoryRepo extends JpaRepository<Accessory,Integer> {
    List<Accessory> findAllByWardrobeID(int wardrobeID);
}

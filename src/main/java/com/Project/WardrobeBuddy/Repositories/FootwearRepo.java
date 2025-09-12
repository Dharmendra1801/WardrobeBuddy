package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.Product;
import com.Project.WardrobeBuddy.Models.ProductType.Accessory;
import com.Project.WardrobeBuddy.Models.ProductType.Footwear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FootwearRepo extends JpaRepository<Footwear,Integer> {
    List<Footwear> findAllByWardrobeID(int wardrobeID);
}

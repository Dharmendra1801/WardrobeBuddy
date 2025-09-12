package com.Project.WardrobeBuddy.Repositories;

import com.Project.WardrobeBuddy.Models.ProductType.Accessory;
import com.Project.WardrobeBuddy.Models.ProductType.Extra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExtraRepo extends JpaRepository<Extra,Integer> {
    List<Extra> findAllByWardrobeID(int wardrobeID);
}

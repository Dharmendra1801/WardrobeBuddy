package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.Models.Category;
import com.Project.WardrobeBuddy.Repositories.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    @Autowired
    UserService userService;

    @Autowired
    CategoryRepo categoryRepo;

    public void add(String username, String categoryName) {
        Category category = new Category();
        category.setCategoryName(categoryName);
        category.setUser(userService.getUser(username));
        categoryRepo.save(category);
    }

    public List<String> getAll(String username) {
        List<Category> catList = categoryRepo.findAllByUserUsername(username).orElse(null);
        if (catList==null) return null;

        List<String> resList = new ArrayList<>();

        for (Category c: catList) {
            resList.add(c.getCategoryName());
        }

        return resList;
    }

    public boolean deleteCategory(String categoryName) {
        Category category = categoryRepo.findById(categoryName).orElse(null);
        if (category==null) return false;

        categoryRepo.deleteById(categoryName);
        return true;
    }
}

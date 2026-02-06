package com.Project.WardrobeBuddy.Models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String username;
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Wardrobe> wardrobeList;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categoryList;
    private String name;
}

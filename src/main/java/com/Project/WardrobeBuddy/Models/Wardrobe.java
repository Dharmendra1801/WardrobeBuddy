package com.Project.WardrobeBuddy.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Wardrobe {

    @Id
    private String username;
    private int clothesID;
    private int footwearsID;
    private int accessoriesID;
    private int extrasID;
}

package com.Project.WardrobeBuddy.Models.ProductType;

import com.Project.WardrobeBuddy.Models.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Accessory extends Product {

    @Id
    private int accessoryID;
    private int wardrobeID;
}

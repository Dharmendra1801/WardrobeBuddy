package com.Project.WardrobeBuddy.Models.ProductType;

import com.Project.WardrobeBuddy.Models.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Clothe extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int clothID;
    private int wardrobeID;
}

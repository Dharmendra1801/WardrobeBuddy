package com.Project.WardrobeBuddy.Models;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class Product {
    private byte[] image;
    private String productName;
    private String brand;
    private Double price;
    private Date bought_date;
    private String category;
    private String note;
}

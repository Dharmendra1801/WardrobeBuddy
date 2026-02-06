package com.Project.WardrobeBuddy.DTOs;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String productName;
    private String brand;
    private Double price;
    private Date bought_date;
    private String category;
    private String note;
    private Integer quantity;
    @Lob
    private byte[] image;
}

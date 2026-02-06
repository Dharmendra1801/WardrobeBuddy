package com.Project.WardrobeBuddy.Models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "wardrobe_id", nullable = false)
    private Wardrobe wardrobe;
    private String productName;
    private String brand;
    private Double price;
    private Date bought_date;
    private String category;
    private String note;
    private Integer quantity;
//    private byte[] image;
}

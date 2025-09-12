package com.Project.WardrobeBuddy.Models;

import com.Project.WardrobeBuddy.Models.ProductType.Accessory;
import com.Project.WardrobeBuddy.Models.ProductType.Clothe;
import com.Project.WardrobeBuddy.Models.ProductType.Extra;
import com.Project.WardrobeBuddy.Models.ProductType.Footwear;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@MappedSuperclass
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Clothe.class, name = "clothes"),
        @JsonSubTypes.Type(value = Footwear.class, name = "footwear"),
        @JsonSubTypes.Type(value = Accessory.class, name = "accessory"),
        @JsonSubTypes.Type(value = Extra.class, name = "extra")
})
public class Product {
//    private byte[] image;
    private String productName;
    private String brand;
    private Double price;
    private Date bought_date;
    private String category;
    private String note;
}

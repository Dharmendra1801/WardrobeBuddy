package com.Project.WardrobeBuddy.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class WardrobeDTO {
    private String wardrobeName;
    private Date dateCreated;
    private String note;
    private Integer noOfProducts;
}

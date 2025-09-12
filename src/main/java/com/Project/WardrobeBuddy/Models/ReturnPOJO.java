package com.Project.WardrobeBuddy.Models;

import com.Project.WardrobeBuddy.Models.ProductType.Accessory;
import com.Project.WardrobeBuddy.Models.ProductType.Clothe;
import com.Project.WardrobeBuddy.Models.ProductType.Extra;
import com.Project.WardrobeBuddy.Models.ProductType.Footwear;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnPOJO {
    private List<Accessory> accessoryList;
    private List<Clothe> clotheList;
    private List<Extra> extraList;
    private List<Footwear> footwearList;
}

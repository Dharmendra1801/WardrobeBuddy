package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.Models.Product;
import com.Project.WardrobeBuddy.Models.ProductType.Accessory;
import com.Project.WardrobeBuddy.Models.ProductType.Clothe;
import com.Project.WardrobeBuddy.Models.ProductType.Extra;
import com.Project.WardrobeBuddy.Models.ProductType.Footwear;
import com.Project.WardrobeBuddy.Models.ReturnPOJO;
import com.Project.WardrobeBuddy.Repositories.AccessoryRepo;
import com.Project.WardrobeBuddy.Repositories.ClotheRepo;
import com.Project.WardrobeBuddy.Repositories.ExtraRepo;
import com.Project.WardrobeBuddy.Repositories.FootwearRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    WardrobeServices wardrobeServices;

    @Autowired
    AccessoryRepo accessoryRepo;

    @Autowired
    ClotheRepo clotheRepo;

    @Autowired
    ExtraRepo extraRepo;

    @Autowired
    FootwearRepo footwearRepo;

    private int getWardrobeID(String username, String wardrobeName) {
        return wardrobeServices.getWardrobeID(username,wardrobeName);
    }

    public ReturnPOJO getAllProducts(String username, String wardrobeName) {
        int wardrobeID = getWardrobeID(username,wardrobeName);

        List<Accessory> accessoryList = accessoryRepo.findAllByWardrobeID(wardrobeID);
        List<Clothe> clotheList = clotheRepo.findAllByWardrobeID(wardrobeID);
        List<Extra> extraList = extraRepo.findAllByWardrobeID(wardrobeID);
        List<Footwear> footwearList = footwearRepo.findAllByWardrobeID(wardrobeID);

        ReturnPOJO result = new ReturnPOJO();
        result.setAccessoryList(accessoryList);
        result.setClotheList(clotheList);
        result.setExtraList(extraList);
        result.setFootwearList(footwearList);
        return result;
    }

    private ReturnPOJO getAllAccessory(String username, String wardrobeName) {
        int wardrobeID = getWardrobeID(username,wardrobeName);

        ReturnPOJO result = new ReturnPOJO();
        result.setAccessoryList(accessoryRepo.findAllByWardrobeID(wardrobeID));
        return result;
    }

    private ReturnPOJO getAllClothe(String username, String wardrobeName) {
        int wardrobeID = getWardrobeID(username,wardrobeName);

        ReturnPOJO result = new ReturnPOJO();
        result.setClotheList(clotheRepo.findAllByWardrobeID(wardrobeID));
        return result;
    }

    private ReturnPOJO getAllExtra(String username, String wardrobeName) {
        int wardrobeID = getWardrobeID(username,wardrobeName);

        ReturnPOJO result = new ReturnPOJO();
        result.setExtraList(extraRepo.findAllByWardrobeID(wardrobeID));
        return result;
    }

    private ReturnPOJO getAllFootwear(String username, String wardrobeName) {
        int wardrobeID = getWardrobeID(username,wardrobeName);

        ReturnPOJO result = new ReturnPOJO();
        result.setFootwearList(footwearRepo.findAllByWardrobeID(wardrobeID));
        return result;
    }

    public ReturnPOJO getProducts(String username, String wardrobeName, String productType) {
        return switch (productType.toLowerCase()) {
            case "accessory" -> getAllAccessory(username, wardrobeName);
            case "clothe" -> getAllClothe(username, wardrobeName);
            case "extra" -> getAllExtra(username, wardrobeName);
            case "footwear" -> getAllFootwear(username, wardrobeName);
            default -> new ReturnPOJO();
        };
    }

    public Product getIndiProduct(String productType, int productID) {
        return switch (productType.toLowerCase()) {
            case "accessory" -> getAccessory(productID);
            case "clothe" -> getClothe(productID);
            case "extra" -> getExtra(productID);
            case "footwear" -> getFootwear(productID);
            default -> new Product();
        };
    }

    private Footwear getFootwear(int productID) {
        return footwearRepo.findById(productID).orElse(new Footwear());
    }

    private Extra getExtra(int productID) {
        return extraRepo.findById(productID).orElse(new Extra());
    }

    private Clothe getClothe(int productID) {
        return clotheRepo.findById(productID).orElse(new Clothe());
    }

    private Accessory getAccessory(int productID) {
        return accessoryRepo.findById(productID).orElse(new Accessory());
    }

    public void addProduct(String username, String wardrobeName, String productType, Product product) {
        int wardrobeID = getWardrobeID(username,wardrobeName);

        switch (productType.toLowerCase()) {
            case "accessory" -> addAccessory(wardrobeID, (Accessory) product);
            case "clothe" -> addClothe(wardrobeID, (Clothe) product);
            case "extra" -> addExtra(wardrobeID, (Extra) product);
            case "footwear" -> addFootwear(wardrobeID, (Footwear) product);
        };
    }

    private void addFootwear(int wardrobeID, Footwear product) {
        product.setWardrobeID(wardrobeID);
        footwearRepo.save((Footwear) product);
    }

    private void addExtra(int wardrobeID, Extra product) {
        product.setWardrobeID(wardrobeID);
        extraRepo.save((Extra) product);
    }

    private void addClothe(int wardrobeID, Clothe product) {
        product.setWardrobeID(wardrobeID);
        clotheRepo.save((Clothe) product);
    }

    private void addAccessory(int wardrobeID, Accessory product) {
        product.setWardrobeID(wardrobeID);
        accessoryRepo.save((Accessory) product);
    }

    public boolean deleteProduct(int productID, String productType) {
        return switch (productType.toLowerCase()) {
            case "accessory" -> deleteAccessory(productID);
            case "clothe" -> deleteClothe(productID);
            case "extra" -> deleteExtra(productID);
            case "footwear" -> deleteFootwear(productID);
            default -> false;
        };
    }

    private boolean deleteAccessory(int productID) {
        if (accessoryRepo.existsById(productID)) {
            accessoryRepo.deleteById(productID);
            return true;
        }
        return false;
    }

    private boolean deleteClothe(int productID) {
        if (clotheRepo.existsById(productID)) {
            clotheRepo.deleteById(productID);
            return true;
        }
        return false;
    }

    private boolean deleteExtra(int productID) {
        if (extraRepo.existsById(productID)) {
            extraRepo.deleteById(productID);
            return true;
        }
        return false;
    }

    private boolean deleteFootwear(int productID) {
        if (extraRepo.existsById(productID)) {
            extraRepo.deleteById(productID);
            return true;
        }
        return false;
    }

    public boolean updateProduct(String username, String wardrobeName, Product product, int productID, String productType) {
        if (!deleteProduct(productID,productType)) return false;
        addProduct(username,wardrobeName,productType,product);
        return true;
    }
}



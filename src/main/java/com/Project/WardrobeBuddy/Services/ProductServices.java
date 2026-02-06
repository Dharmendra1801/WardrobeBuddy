package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.DTOs.ProductDTO;
import com.Project.WardrobeBuddy.Models.Product;
import com.Project.WardrobeBuddy.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductServices {

    @Autowired
    WardrobeServices wardrobeServices;

    @Autowired
    ProductRepo productRepo;

//    private int getWardrobeID(String username, String wardrobeName) {
//        return wardrobeServices.getWardrobeID(username,wardrobeName);
//    }

    public List<ProductDTO> getAllProducts(String username, String wardrobeName) {
        List<Product> productList = productRepo.findAllByWardrobeUserUsernameAndWardrobeWardrobeName(username,wardrobeName).orElse(null);
        if (productList==null) return null;

        List<ProductDTO> returnList = new ArrayList<>();
        for (Product p: productList) {
            returnList.add(convertProductToDTO(p));
        }
        return returnList;
    }

//    private ReturnPOJO getAllAccessory(String username, String wardrobeName) {
//        int wardrobeID = getWardrobeID(username,wardrobeName);
//
//        ReturnPOJO result = new ReturnPOJO();
//        result.setAccessoryList(accessoryRepo.findAllByWardrobeID(wardrobeID));
//        return result;
//    }
//
//    private ReturnPOJO getAllClothe(String username, String wardrobeName) {
//        int wardrobeID = getWardrobeID(username,wardrobeName);
//
//        ReturnPOJO result = new ReturnPOJO();
//        result.setClotheList(clotheRepo.findAllByWardrobeID(wardrobeID));
//        return result;
//    }
//
//    private ReturnPOJO getAllExtra(String username, String wardrobeName) {
//        int wardrobeID = getWardrobeID(username,wardrobeName);
//
//        ReturnPOJO result = new ReturnPOJO();
//        result.setExtraList(extraRepo.findAllByWardrobeID(wardrobeID));
//        return result;
//    }
//
//    private ReturnPOJO getAllFootwear(String username, String wardrobeName) {
//        int wardrobeID = getWardrobeID(username,wardrobeName);
//
//        ReturnPOJO result = new ReturnPOJO();
//        result.setFootwearList(footwearRepo.findAllByWardrobeID(wardrobeID));
//        return result;
//    }
//
//    public ReturnPOJO getProducts(String username, String wardrobeName, String productType) {
//        return switch (productType.toLowerCase()) {
//            case "accessory" -> getAllAccessory(username, wardrobeName);
//            case "clothe" -> getAllClothe(username, wardrobeName);
//            case "extra" -> getAllExtra(username, wardrobeName);
//            case "footwear" -> getAllFootwear(username, wardrobeName);
//            default -> new ReturnPOJO();
//        };
//    }
//
//    public Product getIndiProduct(String productType, int productID) {
//        return switch (productType.toLowerCase()) {
//            case "accessory" -> getAccessory(productID);
//            case "clothe" -> getClothe(productID);
//            case "extra" -> getExtra(productID);
//            case "footwear" -> getFootwear(productID);
//            default -> new Product();
//        };
//    }
//
//    private Footwear getFootwear(int productID) {
//        return footwearRepo.findById(productID).orElse(new Footwear());
//    }
//
//    private Extra getExtra(int productID) {
//        return extraRepo.findById(productID).orElse(new Extra());
//    }
//
//    private Clothe getClothe(int productID) {
//        return clotheRepo.findById(productID).orElse(new Clothe());
//    }
//
//    private Accessory getAccessory(int productID) {
//        return accessoryRepo.findById(productID).orElse(new Accessory());
//    }
//
//    public void addProduct(String username, String wardrobeName, String productType, Product product) {
//        int wardrobeID = getWardrobeID(username,wardrobeName);
//
//        switch (productType.toLowerCase()) {
//            case "accessory" -> addAccessory(wardrobeID, (Accessory) product);
//            case "clothe" -> addClothe(wardrobeID, (Clothe) product);
//            case "extra" -> addExtra(wardrobeID, (Extra) product);
//            case "footwear" -> addFootwear(wardrobeID, (Footwear) product);
//        };
//    }
//
//    private void addFootwear(int wardrobeID, Footwear product) {
//        product.setWardrobeID(wardrobeID);
//        footwearRepo.save((Footwear) product);
//    }
//
//    private void addExtra(int wardrobeID, Extra product) {
//        product.setWardrobeID(wardrobeID);
//        extraRepo.save((Extra) product);
//    }
//
//    private void addClothe(int wardrobeID, Clothe product) {
//        product.setWardrobeID(wardrobeID);
//        clotheRepo.save((Clothe) product);
//    }
//
//    private void addAccessory(int wardrobeID, Accessory product) {
//        product.setWardrobeID(wardrobeID);
//        accessoryRepo.save((Accessory) product);
//    }
//
//    public boolean deleteProduct(int productID, String productType) {
//        return switch (productType.toLowerCase()) {
//            case "accessory" -> deleteAccessory(productID);
//            case "clothe" -> deleteClothe(productID);
//            case "extra" -> deleteExtra(productID);
//            case "footwear" -> deleteFootwear(productID);
//            default -> false;
//        };
//    }
//
//    private boolean deleteAccessory(int productID) {
//        if (accessoryRepo.existsById(productID)) {
//            accessoryRepo.deleteById(productID);
//            return true;
//        }
//        return false;
//    }
//
//    private boolean deleteClothe(int productID) {
//        if (clotheRepo.existsById(productID)) {
//            clotheRepo.deleteById(productID);
//            return true;
//        }
//        return false;
//    }
//
//    private boolean deleteExtra(int productID) {
//        if (extraRepo.existsById(productID)) {
//            extraRepo.deleteById(productID);
//            return true;
//        }
//        return false;
//    }
//
//    private boolean deleteFootwear(int productID) {
//        if (extraRepo.existsById(productID)) {
//            extraRepo.deleteById(productID);
//            return true;
//        }
//        return false;
//    }

//    public boolean updateProduct(String username, String wardrobeName, Product product, int productID, String productType) {
//        if (!deleteProduct(productID,productType)) return false;
//        addProduct(username,wardrobeName,productType,product);
//        return true;
//    }


    private ProductDTO convertProductToDTO(Product p) {
        if (p==null) return null;
        ProductDTO product = new ProductDTO();
        product.setProductName(p.getProductName());
        product.setBrand(p.getBrand());
        product.setPrice(p.getPrice());
        product.setCategory(p.getCategory());
        product.setBought_date(p.getBought_date());
        product.setNote(p.getNote());
        product.setQuantity(p.getQuantity());
        product.setId(p.getId());
        return product;
    }

    public List<ProductDTO> getAllProductsOfCategory(String username, String wardrobeName, String category) {
        List<Product> productList = productRepo.findAllByWardrobeUserUsernameAndWardrobeWardrobeNameAndCategory(username,wardrobeName,category).orElse(null);
        if (productList==null) return null;

        List<ProductDTO> returnList = new ArrayList<>();
        for (Product p: productList) {
            returnList.add(convertProductToDTO(p));
        }
        return returnList;
    }

    public ProductDTO getProduct(Long productID) {
        return convertProductToDTO(productRepo.findById(productID).orElse(null));
    }

    public void addProduct(String username, String wardrobeName, ProductDTO prod) {
        Product product = new Product();
        product.setBought_date(prod.getBought_date());
        product.setQuantity(prod.getQuantity());
        product.setProductName(prod.getProductName());
        product.setPrice(prod.getPrice());
        product.setNote(prod.getNote());
        product.setBrand(prod.getBrand());
        product.setCategory(prod.getCategory());
        product.setWardrobe(wardrobeServices.getWardrobeFromRepo(username, wardrobeName));
        productRepo.save(product);
    }

    public boolean deleteProduct(Long productID) {
        if (productRepo.findById(productID).isPresent()) {
            productRepo.deleteById(productID);
            return true;
        }
        return false;
    }

    public boolean updateProduct(Long productID, ProductDTO prod) {
        Product product = productRepo.findById(productID).orElse(null);
        if (product==null) return false;

        if (prod.getProductName()!=null) product.setProductName(prod.getProductName());
        if (prod.getQuantity()!=null) product.setQuantity(prod.getQuantity());
        if (prod.getNote()!=null) product.setNote(prod.getNote());
        if (prod.getBrand()!=null) product.setBrand(prod.getBrand());
        if (prod.getCategory()!=null) product.setCategory(prod.getCategory());
        if (prod.getPrice()!=null) product.setPrice(prod.getPrice());
        if (prod.getBought_date()!=null) product.setBought_date(prod.getBought_date());

        return true;
    }
}







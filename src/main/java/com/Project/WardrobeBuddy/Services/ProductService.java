package com.Project.WardrobeBuddy.Services;

import com.Project.WardrobeBuddy.DTOs.ProductDTO;
import com.Project.WardrobeBuddy.Models.Product;
import com.Project.WardrobeBuddy.Models.Wardrobe;
import com.Project.WardrobeBuddy.Repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    WardrobeService wardrobeService;

    @Autowired
    ProductRepo productRepo;

    public List<ProductDTO> getAllProducts(String username, String wardrobeName) {
        List<Product> productList = productRepo.findAllByWardrobeUserUsernameAndWardrobeWardrobeName(username,wardrobeName).orElse(null);
        if (productList==null) return null;

        List<ProductDTO> returnList = new ArrayList<>();
        for (Product p: productList) {
            returnList.add(convertProductToDTO(p));
        }
        return returnList;
    }

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
        product.setImage(p.getImage());
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

    public boolean addProduct(String username, String wardrobeName, ProductDTO prod) {
        Product product = new Product();
        product.setBought_date(prod.getBought_date());
        product.setQuantity(prod.getQuantity());
        product.setProductName(prod.getProductName());
        product.setPrice(prod.getPrice());
        product.setNote(prod.getNote());
        product.setBrand(prod.getBrand());
        product.setCategory(prod.getCategory());
        Wardrobe ward = wardrobeService.getWardrobeFromRepo(username, wardrobeName);
        if (ward==null) return false;
        product.setWardrobe(ward);
        productRepo.save(product);
        return true;
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

    public boolean addImage(Long productID, MultipartFile image) throws IOException {
        Product product = productRepo.findById(productID).orElse(null);
        if (product==null) return false;

        product.setImage(image.getBytes());
        return true;
    }
}







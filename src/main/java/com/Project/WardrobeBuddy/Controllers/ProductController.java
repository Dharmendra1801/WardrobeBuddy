package com.Project.WardrobeBuddy.Controllers;


import com.Project.WardrobeBuddy.DTOs.ProductDTO;
import com.Project.WardrobeBuddy.Services.ProductServices;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/{username}/{wardrobeName}/product")
public class ProductController {

    @Autowired
    ProductServices productServices;

    @Autowired
    TokenService tokenService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@PathVariable String username,
                                                           @PathVariable String wardrobeName) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());

        List<ProductDTO> result = productServices.getAllProducts(username,wardrobeName);

        if (result==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/{category}")
    public ResponseEntity<List<ProductDTO>> getAllProductsOfCategory(@PathVariable String username,
                                                                     @PathVariable String wardrobeName,
                                                                     @PathVariable String category) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ArrayList<>());

        List<ProductDTO> result = productServices.getAllProductsOfCategory(username,wardrobeName,category);

        if (result==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ArrayList<>());

        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/id/{productID}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable String username,
                                                 @PathVariable Long productID) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ProductDTO());

        ProductDTO product = productServices.getProduct(productID);

        if (product==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ProductDTO());

        return ResponseEntity.status(HttpStatus.OK).body(product);
    }


    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@PathVariable String username,
                                             @PathVariable String wardrobeName,
                                             @RequestBody ProductDTO product) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");

        productServices.addProduct(username,wardrobeName,product);

        return ResponseEntity.status(HttpStatus.CREATED).body("Product Added");
    }

    @DeleteMapping("/delete/{productID}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productID,
                                                @PathVariable String username) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");

        if (productServices.deleteProduct(productID))
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product doesn't exist");
    }

    @PutMapping("/update/{productID}")
    public ResponseEntity<String> updateProduct(@PathVariable String username,
                                                @PathVariable Long productID,
                                                @RequestBody ProductDTO product) {

        if (!tokenService.checkToken(username)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Not Logged In");

        if (productServices.updateProduct(productID,product))
            return ResponseEntity.status(HttpStatus.OK).body("Updated");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product doesn't exist");
    }
}

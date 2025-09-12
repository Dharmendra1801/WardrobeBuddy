package com.Project.WardrobeBuddy.Controllers;


import com.Project.WardrobeBuddy.Models.Product;
import com.Project.WardrobeBuddy.Models.ReturnPOJO;
import com.Project.WardrobeBuddy.Services.ProductServices;
import com.Project.WardrobeBuddy.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/{username}/{wardrobeName}")
public class WardrobeController {

    @Autowired
    ProductServices productServices;

    @Autowired
    TokenService tokenService;

    @GetMapping("/get_all_products")
    public ResponseEntity<ReturnPOJO> getAllProducts(@PathVariable String username,
                                                     @PathVariable String wardrobeName,
                                                     @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReturnPOJO());

        ReturnPOJO result = productServices.getAllProducts(username,wardrobeName);

        if (result==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReturnPOJO());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get_products/{productType}")
    public ResponseEntity<ReturnPOJO> getProducts(@PathVariable String username,
                                                  @PathVariable String wardrobeName,
                                                  @PathVariable String productType,
                                                  @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ReturnPOJO());

        ReturnPOJO result = productServices.getProducts(username,wardrobeName,productType);

        if (result==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ReturnPOJO());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/get_product/{productType}/{productID}")
    public ResponseEntity<Product> getIndiProduct(@PathVariable String productType,
                                                  @PathVariable int productID,
                                                  @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Product());

        Product result = productServices.getIndiProduct(productType,productID);

        if (result==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Product());

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/add_product/{productType}")
    public ResponseEntity<String> addProduct(@PathVariable String username,
                                             @PathVariable String wardrobeName,
                                             @RequestBody Product product,
                                             @PathVariable String productType,
                                             @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required");
        System.out.println("idhar tak");
        productServices.addProduct(username,wardrobeName,productType, product);
        return ResponseEntity.status(HttpStatus.CREATED).body(StringUtils.capitalize(productType.toLowerCase()) +" created");
    }

    @DeleteMapping("/delete/{productType}/{productID}")
    public ResponseEntity<String> deleteProduct(@PathVariable int productID,
                                                @PathVariable String productType,
                                                @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required");

        if (productServices.deleteProduct(productID,productType))
            return ResponseEntity.status(HttpStatus.OK).body("Deleted");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product doesn't exist");
    }

    @PostMapping("/update/{productType}/{productID}")
    public ResponseEntity<String> updateProduct(@PathVariable String username,
                                                @PathVariable String wardrobeName,
                                                @PathVariable int productID,
                                                @PathVariable String productType,
                                                @RequestBody Product product,
                                                @RequestHeader("auth_token") String token) {

        if (!tokenService.tokenCheck(token)) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Login required");

        if (productServices.updateProduct(username,wardrobeName,product,productID,productType))
            return ResponseEntity.status(HttpStatus.OK).body("Updated");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product doesn't exist");
    }
}

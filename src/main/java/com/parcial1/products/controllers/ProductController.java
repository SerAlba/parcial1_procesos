package com.parcial1.products.controllers;

import com.parcial1.products.models.Product;
import com.parcial1.products.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import java.util.*;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping(value = "/product/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        Map response = new HashMap();

        try {
            response.put("message", "Product found");
            response.put("data", productService.getProductById(id));

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Product not found");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listProducts")
    public ResponseEntity getListProducts () {
        Map response = new HashMap();

        try {
            response.put("message", "Products found");
            response.put("data", productService.allProducts());

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "There are no products");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/createProduct")
    public ResponseEntity createProduct () {
        Map response = new HashMap();

        try {
            List<Product> newProducts = productService.createProduct();

            response.put("message", "Products installed successfully.");
            response.put("data", newProducts);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "An error occurred in the insertion of the products, please try again.");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Map<String, Object> response = new HashMap<>();
        try {
            Product updatedProduct = productService.updateProduct(id, product);
            response.put("message", "Product updated successfully");
            response.put("data", updatedProduct);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("message", e.getMessage());

            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }
}

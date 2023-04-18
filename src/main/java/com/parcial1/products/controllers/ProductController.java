package com.parcial1.products.controllers;

import com.parcial1.products.services.ProductServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.Map;

@RestController
public class ProductController {
    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping(value = "/product/{id}")
    public ResponseEntity getById(@PathVariable(name = "id") Long id) {
        Map response = new HashMap();

        try {
            response.put("message", "Product found");
            response.put("data", productServiceImp.getProductById(id));

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
            response.put("data", productServiceImp.allProducts());

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "There are no products");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "/createProduct")
    public ResponseEntity createProduct (@RequestBody Object idProduct) {
        Map response = new HashMap();

        System.out.print(idProduct);

        response.put("data", idProduct);

        return new ResponseEntity(response, HttpStatus.OK);
    }
}

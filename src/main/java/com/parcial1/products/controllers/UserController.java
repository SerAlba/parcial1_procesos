package com.parcial1.products.controllers;

import com.parcial1.products.models.Product;
import com.parcial1.products.models.User;
import com.parcial1.products.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
        Map response = new HashMap();

        try {
            User updatedUser = userService.updateUser(id, user);
            response.put("message", "User updated successfully");
            response.put("data", updatedUser);

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            response.put("message", "An error occurred while updating the user");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listUsers")
    public ResponseEntity getListUsers () {
        Map response = new HashMap();

        try {
            response.put("message", "List users found");
            response.put("data", userService.getAllUser());

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "There are no users");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity getUserById(@PathVariable(name = "id") Long id) {
        Map response = new HashMap();

        try {
            response.put("message", "User found");
            response.put("data", userService.getUserById(id));

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "User not found");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}

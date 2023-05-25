package com.parcial1.products.controllers;

import com.parcial1.products.models.User;
import com.parcial1.products.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity register(@RequestBody User user) {
        Map response = new HashMap();

        try {
            response.put("message", "Congratulations, successfully registered user");
            response.put("data", userService.register(user));

            return new ResponseEntity(response, HttpStatus.OK);
        } catch (Exception e) {
            response.put("message", "Sorry, an error occurred while registering the user");
            response.put("data", e.getMessage());

            return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
        }
    }
}

package com.parcial1.products.controllers;

import com.parcial1.products.models.User;
import com.parcial1.products.services.UserService;
import com.parcial1.products.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTUtil jwtUtil;

    @PutMapping(value = "/updateUser/{id}")
    public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Authentication token is required");
            response.put("data", Collections.emptyMap());
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

        if (!validateToken(token)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid token");
            response.put("data", Collections.emptyMap());
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

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
    public ResponseEntity getListUsers (@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Authentication token is required");
            response.put("data", Collections.emptyMap());
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

        if (!validateToken(token)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid token");
            response.put("data", Collections.emptyMap());
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

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
    public ResponseEntity getUserById(@PathVariable(name = "id") Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || token.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Authentication token is required");
            response.put("data", Collections.emptyMap());
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

        if (!validateToken(token)) {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Invalid token");
            response.put("data", Collections.emptyMap());
            return new ResponseEntity(response, HttpStatus.UNAUTHORIZED);
        }

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

    private boolean validateToken(String token) {
        // Use the JWTUtil to validate the token
        return jwtUtil.validateToken(token);
    }
}

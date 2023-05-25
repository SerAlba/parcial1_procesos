package com.parcial1.products.services;

import com.parcial1.products.models.User;

import java.util.List;

public interface UserService {
    User register(User user);
    String login(User user);
    User updateUser(User user);
    List<User> getAllUser();
}

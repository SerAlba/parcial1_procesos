package com.parcial1.products.services;

import com.parcial1.products.models.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    User register(User user);
    Optional login(User user);
    User updateUser(Long id, User user);
    List<User> getAllUser();
    User getUserById(Long id);
}

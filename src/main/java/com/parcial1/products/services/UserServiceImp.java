package com.parcial1.products.services;

import com.parcial1.products.models.User;
import com.parcial1.products.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }
}

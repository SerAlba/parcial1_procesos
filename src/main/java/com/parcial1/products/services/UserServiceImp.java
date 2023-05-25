package com.parcial1.products.services;

import com.parcial1.products.models.User;
import com.parcial1.products.repository.UserRepository;
import com.parcial1.products.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JWTUtil jwtUtil;
    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public String login(User user) {
        Optional<User> userBD = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (userBD.isEmpty()) {
            throw new RuntimeException("Invalid authentication credentials");
        }

        return jwtUtil.create(String.valueOf(userBD.get().getId()), String.valueOf(userBD.get().getEmail()));
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

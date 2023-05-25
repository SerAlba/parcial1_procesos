package com.parcial1.products.services;

import com.parcial1.products.models.Product;
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
    public User updateUser(Long id, User user) {
        User userBD = userRepository.findById(id).get();

        if (user.getFirstName() != null) {
            userBD.setFirstName(user.getFirstName());
        }
        if (user.getLastName() != null) {
            userBD.setLastName(user.getLastName());
        }
        if (user.getEmail() != null) {
            userBD.setEmail(user.getEmail());
        }
        if (user.getPassword() != null) {
            userBD.setPassword(user.getPassword());
        }
        if (user.getAddress() != null) {
            userBD.setAddress(user.getAddress());
        }
        if (user.getBirthday() != null) {
            userBD.setBirthday(user.getBirthday());
        }

        return userRepository.save(userBD);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}

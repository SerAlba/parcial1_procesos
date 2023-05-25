package com.parcial1.products.repository;

import com.parcial1.products.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

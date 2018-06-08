package com.example.confeo.repository;

import com.example.confeo.model.Role;
import com.example.confeo.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
    List<User> findByRole(Role role);
}

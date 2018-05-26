package com.example.confeo.repository;

import com.example.confeo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}

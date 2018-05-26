package com.example.confeo.service;

import com.example.confeo.model.Role;
import com.example.confeo.model.User;
import com.example.confeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by mstobieniecka on 2018-05-26.
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void save(User user, Role role) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRole(role);
        userRepository.save(user);
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }
}

package com.example.confeo.service;

import com.example.confeo.exception.EmailAlreadyExists;
import com.example.confeo.model.Role;
import com.example.confeo.model.User;
import com.example.confeo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void save(User user) throws EmailAlreadyExists {
        User userByEmail = userRepository.findByEmail(user.getEmail());
        if(userByEmail == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
        } else {
            throw new EmailAlreadyExists();
        }
    }

    public User findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

	public List<User> getPrelegents() {
		List<User> participants = getAllParticipants();
		List<User> prelegents = new ArrayList<User>();
		for (User participant: participants) {
			if (!participant.getPrelections().isEmpty()){
				prelegents.add(participant);
			}
		}
		return prelegents;
	}
	
	public List<User> getAllParticipants() {
		return userRepository.findByRole(Role.ROLE_PARTICIPANT);
	}
}

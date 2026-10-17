package com.skillpact.skillpact_backend.service;

import com.skillpact.skillpact_backend.entity.User;
import com.skillpact.skillpact_backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {

        if (user.getWalletPoints() == null) {
            user.setWalletPoints(100);
        }
        return userRepository.save(user);
    }
}

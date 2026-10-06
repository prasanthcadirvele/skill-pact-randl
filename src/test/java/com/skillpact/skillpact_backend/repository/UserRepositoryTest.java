package com.skillpact.skillpact_backend.repository;

import com.skillpact.skillpact_backend.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void shouldSaveAndFindUserByUsername() {
        // Given : Un nouvel utilisateur
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@skillpact.com");
        user.setPasswordHash("hashedpassword");
        user.setWalletPoints(100);

        // When : On l'enregistre via le repository
        userRepository.save(user);

        // Then : On vérifie qu'on peut le retrouver par son username
        Optional<User> foundUser = userRepository.findByUsername("testuser");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("testuser@skillpact.com");
    }
}
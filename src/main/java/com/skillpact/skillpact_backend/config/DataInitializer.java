package com.skillpact.skillpact_backend.config;

import com.skillpact.skillpact_backend.entity.Pact;
import com.skillpact.skillpact_backend.entity.Task;
import com.skillpact.skillpact_backend.entity.User;
import com.skillpact.skillpact_backend.repository.PactRepository;
import com.skillpact.skillpact_backend.repository.TaskRepository;
import com.skillpact.skillpact_backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
                                      PactRepository pactRepository,
                                      TaskRepository taskRepository) {
        return args -> {
            // On vérifie si la base est vide pour éviter de recréer les mêmes données à chaque redémarrage
            if (userRepository.count() == 0) {

                // 1. Créer un utilisateur test
                User user = new User();
                user.setUsername("prasanth");
                user.setEmail("prasanth@skillpact.com");
                user.setPasswordHash("fakepasswordhash");
                user.setWalletPoints(150);
                user = userRepository.save(user);

                // 2. Créer un pacte lié à cet utilisateur
                Pact pact = new Pact();
                pact.setTitle("Maîtriser l'architecture Spring Boot & JPA");
                pact.setStatus("ACTIVE");
                pact.setStakedPoints(50);
                pact.setDeadline(LocalDateTime.now().plusDays(7));
                pact.setUser(user);
                pact = pactRepository.save(pact);

                // 3. Créer des tâches liées à ce pacte
                Task task1 = new Task();
                task1.setTitle("Configurer PostgreSQL avec Docker");
                task1.setDescription("Mettre en place le fichier docker-compose et l'application.yml");
                task1.setStatus("DONE");
                task1.setOrderIndex(1);
                task1.setPact(pact);
                taskRepository.save(task1);

                Task task2 = new Task();
                task2.setTitle("Modéliser les entités JPA");
                task2.setDescription("Créer les entités User, Pact et Task avec leurs relations");
                task2.setStatus("IN_REVIEW");
                task2.setOrderIndex(2);
                task2.setPact(pact);
                taskRepository.save(task2);

                System.out.println("✅ Données de test insérées avec succès en base de données !");
            }
        };
    }
}
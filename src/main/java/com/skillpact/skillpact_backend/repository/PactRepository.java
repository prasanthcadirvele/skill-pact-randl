package com.skillpact.skillpact_backend.repository;

import com.skillpact.skillpact_backend.entity.Pact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PactRepository extends JpaRepository<Pact, Long> {
    // Permet de récupérer tous les pactes d'un utilisateur spécifique
    List<Pact> findByUserId(Long userId);
}
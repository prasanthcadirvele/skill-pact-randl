package com.skillpact.skillpact_backend.repository;

import com.skillpact.skillpact_backend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // Récupérer toutes les tâches d'un pacte donné
    List<Task> findByPactId(Long pactId);
}
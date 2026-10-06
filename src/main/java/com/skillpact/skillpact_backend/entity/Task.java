package com.skillpact.skillpact_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String status = "TODO"; // TODO, IN_REVIEW, DONE

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Relation : Plusieurs tâches appartiennent à un seul pacte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pact_id", nullable = false)
    private Pact pact;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
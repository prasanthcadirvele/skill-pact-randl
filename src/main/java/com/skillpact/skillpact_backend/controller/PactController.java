package com.skillpact.skillpact_backend.controller;

import com.skillpact.skillpact_backend.entity.Pact;
import com.skillpact.skillpact_backend.service.PactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacts")
public class PactController {

    private final PactService pactService;

    public PactController(PactService pactService) {
        this.pactService = pactService;
    }

    // GET /api/pacts/user/{userId} : Liste des pactes d'un utilisateur
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Pact>> getPactsByUserId(@PathVariable Long userId) {
        List<Pact> pacts = pactService.getPactsByUserId(userId);
        return ResponseEntity.ok(pacts);
    }

    // POST /api/pacts/user/{userId} : Créer un pacte avec staking de points
    @PostMapping("/user/{userId}")
    public ResponseEntity<Pact> createPact(@PathVariable Long userId, @RequestBody Pact pact) {
        Pact createdPact = pactService.createPact(userId, pact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPact);
    }
}
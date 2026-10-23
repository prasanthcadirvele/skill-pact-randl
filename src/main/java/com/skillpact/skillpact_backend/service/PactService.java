package com.skillpact.skillpact_backend.service;

import com.skillpact.skillpact_backend.entity.Pact;
import com.skillpact.skillpact_backend.entity.User;
import com.skillpact.skillpact_backend.repository.PactRepository;
import com.skillpact.skillpact_backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PactService {
    private final PactRepository pactRepository;
    private final UserRepository userRepository;

    public PactService(PactRepository pactRepository, UserRepository userRepository) {
        this.pactRepository = pactRepository;
        this.userRepository = userRepository;
    }

    // On récupère tous les pactes d'un utilisateur par son ID
    public List<Pact> getPactsByUserId(Long userId) {
        return pactRepository.findByUserId(userId);
    }

    // Créer un pacte avec la règle de staking
    @Transactional
    public Pact createPact(Long userId, Pact pact) {
        // Vérifier si l'utilisateur existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID: " + userId));

        // Vérifier si l'utilisateur a assez de points pour le staking
        int stakedPoints = pact.getStakedPoints();
        if (user.getWalletPoints() < stakedPoints) {
            throw new RuntimeException("Solde Insuffisant pour le staking. Points disponibles: " + user.getWalletPoints() + ", Points à staker: " + stakedPoints);
        }

        // Statut et deadline par défaut si non renseignés
        if (pact.getStatus() == null) {
            pact.setStatus("ACTIVE");
        }
        if (pact.getDeadline() == null) {
            pact.setDeadline(java.time.LocalDateTime.now().plusDays(7));
        }

        // Déduire les points du portefeuille de l'utilisateur
        user.setWalletPoints(user.getWalletPoints() - stakedPoints);
        userRepository.save(user);

        // Lier le pacte à l'utilisateur et sauvegarder le pacte
        pact.setUser(user);
        return pactRepository.save(pact);
    }
}

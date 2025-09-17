package dev.gaming_club.GamingClub.service;

import dev.gaming_club.GamingClub.model.Game;
import dev.gaming_club.GamingClub.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game createGame(Game gameData) {
        // You can add business logic here, for now we just save it
        return gameRepository.save(gameData);
    }
}
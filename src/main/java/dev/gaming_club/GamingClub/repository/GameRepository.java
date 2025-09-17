package dev.gaming_club.GamingClub.repository;

import dev.gaming_club.GamingClub.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {
}
package dev.gaming_club.GamingClub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "games")
public class Game {

    @Id
    private String id;
    private String name;
    private double price;
    private String description;
    private int minPlayerCount;
    private int maxPlayerCount;
    private int durationInMinutes;
    private GameStatus status;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public int getMinPlayerCount() { return minPlayerCount; }
    public void setMinPlayerCount(int minPlayerCount) { this.minPlayerCount = minPlayerCount; }
    public int getMaxPlayerCount() { return maxPlayerCount; }
    public void setMaxPlayerCount(int maxPlayerCount) { this.maxPlayerCount = maxPlayerCount; }
    public int getDurationInMinutes() { return durationInMinutes; }
    public void setDurationInMinutes(int durationInMinutes) { this.durationInMinutes = durationInMinutes; }
    public GameStatus getStatus() { return status; }
    public void setStatus(GameStatus status) { this.status = status; }
}
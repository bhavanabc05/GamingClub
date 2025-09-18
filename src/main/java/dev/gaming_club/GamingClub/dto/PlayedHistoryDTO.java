package dev.gaming_club.GamingClub.dto;

import java.time.LocalDateTime;

public class PlayedHistoryDTO {
    private String id;
    private LocalDateTime dateTime;
    private String gameName;
    private double amount;
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public String getGameName() { return gameName; }
    public void setGameName(String gameName) { this.gameName = gameName; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
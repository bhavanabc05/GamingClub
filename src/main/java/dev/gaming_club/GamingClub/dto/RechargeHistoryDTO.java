package dev.gaming_club.GamingClub.dto;

import java.time.LocalDateTime;

public class RechargeHistoryDTO {
    private String id;
    private double amount;
    private LocalDateTime dateTime;
    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
}
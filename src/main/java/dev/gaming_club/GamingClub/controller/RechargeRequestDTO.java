package dev.gaming_club.GamingClub.controller;

// This class is a blueprint for the JSON request body
public class RechargeRequestDTO {
    private String memberId;
    private double amount;

    // Getters and Setters
    public String getMemberId() { return memberId; }
    public void setMemberId(String memberId) { this.memberId = memberId; }
    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}
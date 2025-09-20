package dev.gaming_club.GamingClub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "members")
public class Member {

    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String phone;
    private double balance;
    private LocalDateTime joiningDate;
    private String password;

    // --- Paste all the code below this line ---

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public LocalDateTime getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(LocalDateTime joiningDate) {
        this.joiningDate = joiningDate;
    }
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) { 
    	this.password = password; 
    }
}
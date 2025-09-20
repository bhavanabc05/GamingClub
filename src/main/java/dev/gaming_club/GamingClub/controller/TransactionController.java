package dev.gaming_club.GamingClub.controller;

import dev.gaming_club.GamingClub.model.Transaction;
import dev.gaming_club.GamingClub.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:5174")
@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
 // Add this method inside the TransactionController class
    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Map<String, String> payload) {
        String memberId = payload.get("memberId");
        String gameId = payload.get("gameId");

        Transaction newTransaction = transactionService.createTransaction(memberId, gameId);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
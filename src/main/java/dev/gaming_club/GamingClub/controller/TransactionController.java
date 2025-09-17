package dev.gaming_club.GamingClub.controller;

import dev.gaming_club.GamingClub.model.Transaction;
import dev.gaming_club.GamingClub.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Map<String, String> payload) {
        String memberId = payload.get("memberId");
        String gameId = payload.get("gameId");

        Transaction newTransaction = transactionService.createTransaction(memberId, gameId);
        return new ResponseEntity<>(newTransaction, HttpStatus.CREATED);
    }
}
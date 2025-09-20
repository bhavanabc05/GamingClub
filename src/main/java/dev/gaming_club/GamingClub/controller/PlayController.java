package dev.gaming_club.GamingClub.controller;

import dev.gaming_club.GamingClub.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@CrossOrigin("http://localhost:5174")
@RestController
@RequestMapping("/api/v1")
public class PlayController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/play")
    public ResponseEntity<String> playGame(@RequestBody Map<String, String> payload) {
        try {
            transactionService.createTransaction(payload.get("member_id"), payload.get("game_id"));
            return ResponseEntity.ok("Game played successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
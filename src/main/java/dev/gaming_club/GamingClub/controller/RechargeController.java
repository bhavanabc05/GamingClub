package dev.gaming_club.GamingClub.controller;

import dev.gaming_club.GamingClub.model.Recharge;
import dev.gaming_club.GamingClub.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/recharges")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @PostMapping
    public ResponseEntity<Recharge> createRecharge(@RequestBody Map<String, Object> payload) {
        String memberId = (String) payload.get("memberId");
        double amount = Double.parseDouble(payload.get("amount").toString());

        Recharge newRecharge = rechargeService.createRecharge(memberId, amount);
        return new ResponseEntity<>(newRecharge, HttpStatus.CREATED);
    }
}
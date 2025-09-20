package dev.gaming_club.GamingClub.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gaming_club.GamingClub.model.Recharge;
import dev.gaming_club.GamingClub.service.RechargeService;

@CrossOrigin("http://localhost:5174")
@RestController
@RequestMapping("/api/v1/recharges")
public class RechargeController {

    @Autowired
    private RechargeService rechargeService;

    @GetMapping
    public ResponseEntity<List<Recharge>> getAllRecharges() {
        return new ResponseEntity<>(rechargeService.getAllRecharges(), HttpStatus.OK);
    }

    // --- THIS METHOD IS NOW SAFER ---
    @PostMapping
    public ResponseEntity<Recharge> createRecharge(@RequestBody RechargeRequestDTO payload) {
        Recharge newRecharge = rechargeService.createRecharge(payload.getMemberId(), payload.getAmount());
        return new ResponseEntity<>(newRecharge, HttpStatus.CREATED);
    }
}
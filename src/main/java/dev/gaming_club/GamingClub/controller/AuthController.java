package dev.gaming_club.GamingClub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @PostMapping("/auth")
    public ResponseEntity<String> login() {
        // In a real application, you would verify a username/password
        // and generate a real JWT token.
        return ResponseEntity.ok("fake-jwt-token");
    }
}
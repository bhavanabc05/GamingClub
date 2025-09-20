package dev.gaming_club.GamingClub.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gaming_club.GamingClub.exception.ResourceNotFoundException;
import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import dev.gaming_club.GamingClub.service.MemberService;

@CrossOrigin("http://localhost:5174")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @PostMapping("/register")
    public ResponseEntity<Member> registerMember(@RequestBody Map<String, String> payload) {
        // This now correctly calls your service to create a member with a hashed password
        Member newMember = memberService.createMember(
            payload.get("name"),
            payload.get("phone"),
            payload.get("password")
        );
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginMember(@RequestBody Map<String, String> payload) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        Member member = memberRepository.findByName(payload.get("name"))
                .orElseThrow(() -> new ResourceNotFoundException("Invalid name or password"));

        if (passwordEncoder.matches(payload.get("password"), member.getPassword())) {
            return ResponseEntity.ok("Login Successful"); // Passwords match
        } else {
            // Use the same error message for security reasons
            throw new ResourceNotFoundException("Invalid name or password");
        }
    }
}
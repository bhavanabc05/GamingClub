package dev.gaming_club.GamingClub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.gaming_club.GamingClub.dto.MemberProfileDTO;
import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.service.MemberService;

@RestController // Tells Spring this class will handle API requests
@RequestMapping("/api/v1/members") // All URLs for this controller will start with this path
public class MemberController {

    @Autowired // Ask Spring for the MemberService
    private MemberService memberService;

    // This method will handle GET requests to /api/v1/members
    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        // Call the service to get the data, and return it with a 200 OK status
        return new ResponseEntity<List<Member>>(memberService.getAllMembers(), HttpStatus.OK);
    }
    
    // Add this new method inside the MemberController class
    @GetMapping("/{id}")
    public ResponseEntity<Member> getSingleMember(@PathVariable String id) {
        return new ResponseEntity<>(memberService.getSingleMember(id), HttpStatus.OK);
    }

    // This method will handle POST requests to /api/v1/members
    @PostMapping
    public ResponseEntity<Member> createMember(@RequestBody Map<String, String> payload) {
        // Call the service to create a member, and return the created member with a 201 CREATED status
        return new ResponseEntity<Member>(memberService.createMember(payload.get("name"), payload.get("phone")), HttpStatus.CREATED);
    }
    
    // Add this new method inside the MemberController class
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content is a standard response for successful deletion
    }
    
 // In MemberController.java
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        String newName = (String) payload.get("name");
        String newPhone = (String) payload.get("phone");
        
        // Safely get and parse the balance from the request
        double newBalance = 0.0;
        if (payload.get("balance") != null) {
            newBalance = Double.parseDouble(payload.get("balance").toString());
        }

        Member updatedMember = memberService.updateMember(id, newName, newPhone, newBalance);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }
    
    // Add this new method to your MemberController class
    @PostMapping("/search")
    public ResponseEntity<MemberProfileDTO> searchMember(@RequestBody Map<String, String> payload) {
        String phone = payload.get("phone");
        return new ResponseEntity<>(memberService.getMemberProfile(phone), HttpStatus.OK);
    }
}
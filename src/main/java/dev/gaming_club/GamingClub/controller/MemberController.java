package dev.gaming_club.GamingClub.controller;

import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    
    // Add this new method inside the MemberController class
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Map<String, String> payload) {
        String newName = payload.get("name");
        String newPhone = payload.get("phone");
        return new ResponseEntity<>(memberService.updateMember(id, newName, newPhone), HttpStatus.OK);
    }
}
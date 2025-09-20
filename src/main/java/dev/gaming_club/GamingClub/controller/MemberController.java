package dev.gaming_club.GamingClub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

@CrossOrigin("http://localhost:5174")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return new ResponseEntity<>(memberService.getAllMembers(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Member> getSingleMember(@PathVariable String id) {
        return new ResponseEntity<>(memberService.getSingleMember(id), HttpStatus.OK);
    }

    // --- The POST method for creating a member has been REMOVED from this controller ---

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(@PathVariable String id, @RequestBody Map<String, Object> payload) {
        String newName = (String) payload.get("name");
        String newPhone = (String) payload.get("phone");
        double newBalance = 0.0;
        if (payload.get("balance") != null) {
            newBalance = Double.parseDouble(payload.get("balance").toString());
        }
        Member updatedMember = memberService.updateMember(id, newName, newPhone, newBalance);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }
    
    @PostMapping("/search")
    public ResponseEntity<MemberProfileDTO> searchMember(@RequestBody Map<String, String> payload) {
        String phone = payload.get("phone");
        return new ResponseEntity<>(memberService.getMemberProfile(phone), HttpStatus.OK);
    }   
}
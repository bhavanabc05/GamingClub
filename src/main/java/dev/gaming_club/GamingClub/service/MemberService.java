package dev.gaming_club.GamingClub.service;

import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


@Service // Tells Spring that this class is a Service component
public class MemberService {

    @Autowired // Asks Spring to give us the MemberRepository we created
    private MemberRepository memberRepository;

    // A method to get all members
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
 // Add this new method inside the MemberService class
    public void deleteMember(String memberId) {
        // Check if the member exists before trying to delete
        if (!memberRepository.existsById(memberId)) {
            throw new RuntimeException("Member not found with id: " + memberId);
        }
        memberRepository.deleteById(memberId);
    }

    // A method to create a new member (with business logic)
    public Member createMember(String name, String phone) {
        // --- Business Logic ---
        Member newMember = new Member();
        newMember.setName(name);
        newMember.setPhone(phone);
        newMember.setBalance(0.0); // Rule: New members start with a 0 balance.
        newMember.setJoiningDate(LocalDateTime.now()); // Rule: Set the joining date.

        // Use the repository to save the new member to the database
        return memberRepository.save(newMember);
    }
}
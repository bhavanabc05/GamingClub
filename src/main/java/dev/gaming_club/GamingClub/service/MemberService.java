package dev.gaming_club.GamingClub.service;

import dev.gaming_club.GamingClub.exception.ResourceNotFoundException;
import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Member createMember(String name, String phone) {
        Member newMember = new Member();
        newMember.setName(name);
        newMember.setPhone(phone);
        newMember.setBalance(0.0);
        newMember.setJoiningDate(LocalDateTime.now());
        return memberRepository.save(newMember);
    }
    
    public Member getSingleMember(String memberId) {
        // --- UPDATED ---
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));
    }
    
    public void deleteMember(String memberId) {
        // --- UPDATED ---
        if (!memberRepository.existsById(memberId)) {
            throw new ResourceNotFoundException("Member not found with id: " + memberId);
        }
        memberRepository.deleteById(memberId);
    }
    
    public Member updateMember(String memberId, String newName, String newPhone) {
        // --- UPDATED ---
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        member.setName(newName);
        member.setPhone(newPhone);
        
        return memberRepository.save(member);
    }
}
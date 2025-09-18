package dev.gaming_club.GamingClub.service;

import dev.gaming_club.GamingClub.exception.ResourceNotFoundException; // Import our custom exception
import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.model.Recharge;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import dev.gaming_club.GamingClub.repository.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private MemberRepository memberRepository;

    public List<Recharge> getAllRecharges() {
        return rechargeRepository.findAll();
    }

    public Recharge createRecharge(String memberId, double amount) {
        // --- THIS LINE IS UPDATED ---
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        double newBalance = member.getBalance() + amount;
        member.setBalance(newBalance);
        memberRepository.save(member);

        Recharge recharge = new Recharge();
        recharge.setMemberId(memberId);
        recharge.setAmount(amount);
        recharge.setDate(LocalDateTime.now());

        return rechargeRepository.save(recharge);
    }
}
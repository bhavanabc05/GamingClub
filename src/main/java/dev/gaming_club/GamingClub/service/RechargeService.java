package dev.gaming_club.GamingClub.service;

import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.model.Recharge;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import dev.gaming_club.GamingClub.repository.RechargeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RechargeService {

    @Autowired
    private RechargeRepository rechargeRepository;

    @Autowired
    private MemberRepository memberRepository; // We need this to update the member's balance

    public Recharge createRecharge(String memberId, double amount) {
        // 1. Find the member by their ID.
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        // 2. Update the member's balance.
        double newBalance = member.getBalance() + amount;
        member.setBalance(newBalance);
        memberRepository.save(member); // Save the updated member

        // 3. Create and save the recharge record.
        Recharge recharge = new Recharge();
        recharge.setMemberId(memberId);
        recharge.setAmount(amount);
        recharge.setDate(LocalDateTime.now());

        return rechargeRepository.save(recharge);
    }
}
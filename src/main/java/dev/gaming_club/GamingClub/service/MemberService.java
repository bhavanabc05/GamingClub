package dev.gaming_club.GamingClub.service;

import dev.gaming_club.GamingClub.dto.MemberProfileDTO;
import dev.gaming_club.GamingClub.dto.PlayedHistoryDTO;
import dev.gaming_club.GamingClub.dto.RechargeHistoryDTO;
import dev.gaming_club.GamingClub.exception.ResourceNotFoundException;
import dev.gaming_club.GamingClub.model.Game;
import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.model.Recharge;
import dev.gaming_club.GamingClub.model.Transaction;
import dev.gaming_club.GamingClub.repository.GameRepository;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import dev.gaming_club.GamingClub.repository.RechargeRepository;
import dev.gaming_club.GamingClub.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private RechargeRepository rechargeRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private TransactionRepository transactionRepository;


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member createMember(String name, String phone,String plainTextPassword) {
    	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        logger.info("Attempting to create a new member with name: {}", name);
        Member newMember = new Member();
        newMember.setName(name);
        newMember.setPhone(phone);
        newMember.setBalance(0.0);
        newMember.setJoiningDate(LocalDateTime.now());
        newMember.setPassword(passwordEncoder.encode(plainTextPassword));
        Member savedMember = memberRepository.save(newMember);
        logger.info("Successfully created member with ID: {}", savedMember.getId());
        return savedMember;
    }

    public Member getSingleMember(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));
    }

    public void deleteMember(String memberId) {
        if (!memberRepository.existsById(memberId)) {
            throw new ResourceNotFoundException("Member not found with id: " + memberId);
        }
        memberRepository.deleteById(memberId);
    }

 // In MemberService.java
    public Member updateMember(String memberId, String newName, String newPhone, double newBalance) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + memberId));

        // Update all three fields
        member.setName(newName);
        member.setPhone(newPhone);
        member.setBalance(newBalance); // This line is new
        
        return memberRepository.save(member);
    }

    // --- NEW METHOD FOR THE /members/search ENDPOINT ---
    public MemberProfileDTO getMemberProfile(String phone) {
        Member member = memberRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with phone: " + phone));

        // Get recharge history and map to DTO
        List<Recharge> recharges = rechargeRepository.findByMemberId(member.getId());
        List<RechargeHistoryDTO> rechargeHistoryDTOs = recharges.stream().map(r -> {
            RechargeHistoryDTO dto = new RechargeHistoryDTO();
            dto.setId(r.getId());
            dto.setAmount(r.getAmount());
            dto.setDateTime(r.getDate());
            return dto;
        }).collect(Collectors.toList());

        // Get all available games
        List<Game> allGames = gameRepository.findAll();

        // Get played history and map to DTO
        List<Transaction> transactions = transactionRepository.findByMemberId(member.getId());
        List<PlayedHistoryDTO> playedHistoryDTOs = transactions.stream().map(t -> {
            PlayedHistoryDTO dto = new PlayedHistoryDTO();
            // Find the game to get its name for the history
            Game game = gameRepository.findById(t.getGameId()).orElse(new Game());
            dto.setId(t.getId());
            dto.setDateTime(t.getDate());
            dto.setGameName(game.getName());
            dto.setAmount(t.getAmount());
            return dto;
        }).collect(Collectors.toList());

        // Assemble the final profile object to return
        MemberProfileDTO profile = new MemberProfileDTO();
        profile.setMember(member);
        profile.setRechargeHistory(rechargeHistoryDTOs);
        profile.setGames(allGames);
        profile.setPlayedHistory(playedHistoryDTOs);

        return profile;
    }

	public Member updateMember1(String id, String newName, String newPhone, double newBalance) {
		// TODO Auto-generated method stub
		return null;
	}
}
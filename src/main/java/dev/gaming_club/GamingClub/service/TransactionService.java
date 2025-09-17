package dev.gaming_club.GamingClub.service;

import dev.gaming_club.GamingClub.model.Game;
import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.model.Transaction;
import dev.gaming_club.GamingClub.repository.GameRepository;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import dev.gaming_club.GamingClub.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private GameRepository gameRepository;

    public Transaction createTransaction(String memberId, String gameId) {
        // Step 1: Find the member and the game.
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found with id: " + gameId));

        // Step 2: Check if the member has enough balance.
        if (member.getBalance() < game.getPrice()) {
            throw new RuntimeException("Insufficient balance for member: " + member.getName());
        }

        // Step 3: Deduct the game price from the member's balance and save.
        double newBalance = member.getBalance() - game.getPrice();
        member.setBalance(newBalance);
        memberRepository.save(member);

        // Step 4: Create and save the transaction record.
        Transaction transaction = new Transaction();
        transaction.setMemberId(memberId);
        transaction.setGameId(gameId);
        transaction.setAmount(game.getPrice()); // Log the price of the game
        transaction.setDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }
}
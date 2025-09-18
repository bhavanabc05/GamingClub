package dev.gaming_club.GamingClub.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.gaming_club.GamingClub.model.Transaction;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
	List<Transaction> findByMemberId(String memberId);}
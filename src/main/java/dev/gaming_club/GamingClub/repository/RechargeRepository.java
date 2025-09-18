package dev.gaming_club.GamingClub.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import dev.gaming_club.GamingClub.model.Recharge;

@Repository
public interface RechargeRepository extends MongoRepository<Recharge, String> {
	List<Recharge> findByMemberId(String memberId);

	@Query("{ 'date': { $gte: ?0, $lt: ?1 } }")
    List<Recharge> findRechargesBetweenDates(LocalDateTime startDate, LocalDateTime endDate);}
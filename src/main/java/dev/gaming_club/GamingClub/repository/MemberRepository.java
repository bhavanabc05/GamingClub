package dev.gaming_club.GamingClub.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import dev.gaming_club.GamingClub.model.Member;



@Repository
public interface MemberRepository extends MongoRepository<Member,String>{
	Optional<Member> findByPhone(String phone);
	Optional<Member> findByName(String name);
}

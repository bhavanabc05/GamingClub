package dev.gaming_club.GamingClub.repository;

import dev.gaming_club.GamingClub.model.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends MongoRepository<Member,String>{

}

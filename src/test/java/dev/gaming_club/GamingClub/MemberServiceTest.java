package dev.gaming_club.GamingClub;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.repository.MemberRepository;
import dev.gaming_club.GamingClub.service.MemberService;

@ExtendWith(MockitoExtension.class) // This enables Mockito for our tests
class MemberServiceTest {

    @Mock // Creates a fake version of MemberRepository
    private MemberRepository memberRepository;

    @InjectMocks // Creates a real MemberService and injects the fake repository into it
    private MemberService memberService;

    @Test // Marks this method as a test case
    void testCreateMember() {
        // --- 1. Arrange (Setup) ---
        // Create a dummy member object that we expect our mock repository to return
        Member savedMember = new Member();
        savedMember.setId("12345");
        savedMember.setName("Test User");
        savedMember.setPhone("1234567890");
        savedMember.setBalance(0.0);

        // Tell our fake repository how to behave:
        // "When the save() method is called with any Member object, then return our dummy savedMember"
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        // --- 2. Act (Execution) ---
        // Call the actual method we want to test
        Member createdMember = memberService.createMember("Test User", "1234567890");

        // --- 3. Assert (Verification) ---
        // Check if the results are what we expect
        assertNotNull(createdMember); // Check that the created member is not null
        assertEquals("Test User", createdMember.getName()); // Check that the name is correct
        assertEquals(0.0, createdMember.getBalance()); // Check that the balance was set to 0
    }
}
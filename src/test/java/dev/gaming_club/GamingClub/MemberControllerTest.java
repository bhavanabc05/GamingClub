package dev.gaming_club.GamingClub;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import dev.gaming_club.GamingClub.controller.MemberController;
import dev.gaming_club.GamingClub.model.Member;
import dev.gaming_club.GamingClub.service.MemberService;

// This sets up a test environment specifically for the MemberController
@WebMvcTest(MemberController.class)
class MemberControllerTest {

    // Gives us a tool to simulate HTTP requests without a real server
    @Autowired
    private MockMvc mockMvc;

    // Tells Spring to replace the real MemberService with a mock
    @MockBean
    private MemberService memberService;

    @Test
    void testGetAllMembers() throws Exception {
        // --- 1. Arrange (Setup) ---
        // Create a fake member to be returned by our mock service
        Member member = new Member();
        member.setId("1");
        member.setName("Test Member");
        member.setPhone("1122334455");

        // Tell our fake service how to behave:
        // "When the getAllMembers() method is called, then return a list containing our fake member"
        when(memberService.getAllMembers()).thenReturn(List.of(member));

        // --- 2. Act & 3. Assert ---
        // Perform a fake GET request and check the results
        mockMvc.perform(get("/api/v1/members"))
                .andExpect(status().isOk()) // Check for a 200 OK status
                .andExpect(jsonPath("$[0].name").value("Test Member")); // Check that the first member's name is correct
    }
}
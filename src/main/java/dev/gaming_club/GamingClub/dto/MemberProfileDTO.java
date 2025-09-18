package dev.gaming_club.GamingClub.dto;

import java.util.List;

import dev.gaming_club.GamingClub.model.Game;
import dev.gaming_club.GamingClub.model.Member;

public class MemberProfileDTO {
    private Member member;
    private List<RechargeHistoryDTO> rechargeHistory;
    private List<Game> games;
    private List<PlayedHistoryDTO> playedHistory;
    // Getters and Setters
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public List<RechargeHistoryDTO> getRechargeHistory() { return rechargeHistory; }
    public void setRechargeHistory(List<RechargeHistoryDTO> rechargeHistory) { this.rechargeHistory = rechargeHistory; }
    public List<Game> getGames() { return games; }
    public void setGames(List<Game> games) { this.games = games; }
    public List<PlayedHistoryDTO> getPlayedHistory() { return playedHistory; }
    public void setPlayedHistory(List<PlayedHistoryDTO> playedHistory) { this.playedHistory = playedHistory; }
}
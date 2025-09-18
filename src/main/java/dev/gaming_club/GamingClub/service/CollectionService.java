package dev.gaming_club.GamingClub.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.gaming_club.GamingClub.model.Recharge;
import dev.gaming_club.GamingClub.repository.RechargeRepository;

@Service
public class CollectionService {

    @Autowired
    private RechargeRepository rechargeRepository;

    public List<Recharge> getRechargesByDate(String dateString) {
        // This line parses a date string like "2025-09-18" into a date object
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);

        // This uses the custom query we created in RechargeRepository
        // to find all recharges that occurred on that specific day.
        return rechargeRepository.findRechargesBetweenDates(
            date.atStartOfDay(),           // From the beginning of the day (e.g., 2025-09-18T00:00:00)
            date.plusDays(1).atStartOfDay() // To the beginning of the next day (e.g., 2025-09-19T00:00:00)
        );
    }
}
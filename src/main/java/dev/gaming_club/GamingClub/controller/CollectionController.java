package dev.gaming_club.GamingClub.controller;

import dev.gaming_club.GamingClub.model.Recharge;
import dev.gaming_club.GamingClub.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/collection") // Note the singular "collection"
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/{date}")
    public ResponseEntity<List<Recharge>> getCollectionForDate(@PathVariable String date) {
        return new ResponseEntity<>(collectionService.getRechargesByDate(date), HttpStatus.OK);
    }
}
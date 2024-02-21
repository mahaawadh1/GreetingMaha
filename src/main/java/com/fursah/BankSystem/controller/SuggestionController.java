package com.fursah.BankSystem.controller;

import com.fursah.BankSystem.bo.suggestionRequest.CreateSuggestionRequest;
import com.fursah.BankSystem.entity.GuestSuggestionEntity;
import com.fursah.BankSystem.service.suggestion.GuestSuggestionService;
import com.fursah.BankSystem.service.suggestion.SuggestionProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/suggestions")
public class SuggestionController {
    @Autowired
    private SuggestionProcessor suggestionProcessor;

    @GetMapping("/byDate")
    public List<GuestSuggestionEntity> getGuestsByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return suggestionService.findGuestsByDate(date);
    }

    @GetMapping("/currentMonth")
    public List<GuestSuggestionEntity> getGuestsVisitedCurrentMonth() {
        return suggestionService.findGuestsVisitedCurrentMonth();
    }

    @GetMapping("/byName")
    public List<GuestSuggestionEntity> getGuestsByName(@RequestParam("name") String name) {
        return suggestionService.findGuestsByName(name);
    }

    private final GuestSuggestionService suggestionService;

    @Autowired
    public SuggestionController(GuestSuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @PostMapping("/suggest")
    public ResponseEntity<List<GuestSuggestionEntity>> handleSuggestions(@RequestBody CreateSuggestionRequest suggestionRequest) {
        List<GuestSuggestionEntity> suggestionList = suggestionService.printAndProcessSuggestion(suggestionRequest.getSuggestionText());
        return ResponseEntity.ok(suggestionList);
    }

    @PostMapping("/create-suggestion")
    public ResponseEntity<String> processSuggestion(CreateSuggestionRequest suggestionText) {
        suggestionService.processSuggestion(suggestionText);
        return ResponseEntity.ok("you created a suggestion");
    }

}
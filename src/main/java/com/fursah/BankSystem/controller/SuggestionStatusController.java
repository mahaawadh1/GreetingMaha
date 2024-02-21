package com.fursah.BankSystem.controller;

import com.fursah.BankSystem.entity.GuestSuggestionEntity;
import com.fursah.BankSystem.service.suggestion.GuestSuggestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/suggestions_Status")
public class SuggestionStatusController {

    private final GuestSuggestionService suggestionService;

    @Autowired
    public SuggestionStatusController(GuestSuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/retrieve")
    public List<GuestSuggestionEntity> getAllDistinctSuggestions() {
        return suggestionService.findAllDataSuggestions();
    }
    @GetMapping("/created")
    public List<GuestSuggestionEntity> getCreatedSuggestions() {
        return suggestionService.findAllCreatedSuggestions();
    }

    @GetMapping("/removed")
    public List<GuestSuggestionEntity> getRemovedSuggestions() {
        return suggestionService.findAllRemovedSuggestions();
    }
}
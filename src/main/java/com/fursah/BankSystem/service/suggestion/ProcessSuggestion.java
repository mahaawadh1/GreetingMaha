package com.fursah.BankSystem.service.suggestion;

import com.fursah.BankSystem.bo.suggestionRequest.CreateSuggestionRequest;
import com.fursah.BankSystem.entity.GuestSuggestionEntity;

import java.time.LocalDate;
import java.util.List;

public interface ProcessSuggestion {
    void processSuggestion(CreateSuggestionRequest suggestionTex);
    List<GuestSuggestionEntity>findGuestsByDate(LocalDate date);
}
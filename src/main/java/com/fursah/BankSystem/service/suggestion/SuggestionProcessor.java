package com.fursah.BankSystem.service.suggestion;

import com.fursah.BankSystem.entity.GuestSuggestionEntity;

import java.time.LocalDate;
import java.util.List;


public interface SuggestionProcessor {
    List<GuestSuggestionEntity>findGuestsByDate(LocalDate date);

    void getSuggestionRate ();
    List<GuestSuggestionEntity> getCreateStatusSuggestions();
    List<GuestSuggestionEntity> getRemoveStatusSuggestions();
    List<GuestSuggestionEntity>findGuestsVisitedCurrentMonth();
    List<GuestSuggestionEntity>findGuestsByName(String name);
}

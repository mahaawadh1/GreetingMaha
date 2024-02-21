package com.fursah.BankSystem.reposatriy;

import com.fursah.BankSystem.entity.GuestSuggestionEntity;
import com.fursah.BankSystem.util.enums.SuggestionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GuestSuggestionRepository extends JpaRepository<GuestSuggestionEntity, Long> {
    List<GuestSuggestionEntity> findBySuggestionsStatus(SuggestionStatus status);

    List<GuestSuggestionEntity> findByDateOfvisit(LocalDate date);
    List<GuestSuggestionEntity>findByDateOfvisitBetween(LocalDate startDate,LocalDate endDate);
    List<GuestSuggestionEntity> findByNameContainingIgnoreCase(String name);

}
package com.fursah.BankSystem.entity;

import com.fursah.BankSystem.util.enums.SuggestionStatus;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "suggestions")
public class GuestSuggestionEntity {
    public GuestSuggestionEntity() {

    }

    @Id
    @Column(name = "id",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rate",nullable = false)
    private String rate;
    @Column(name = "suggestion_text",nullable = false)
    private String suggestionText;
    @Enumerated(EnumType.STRING)
    @Column(name = "suggestion_status",nullable = false)
    private SuggestionStatus suggestionsStatus;

    public LocalDate getDateofvisit() {
        return dateofvisit;
    }

    public void setDateofvisit(LocalDate dateofvisit) {
        this.dateofvisit = dateofvisit;
    }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
private LocalDate dateofvisit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public SuggestionStatus getSuggestionsStatus() {
        return suggestionsStatus;
    }

    public void setSuggestionsStatus(SuggestionStatus suggestionsStatus) {
        this.suggestionsStatus = suggestionsStatus;
    }
}
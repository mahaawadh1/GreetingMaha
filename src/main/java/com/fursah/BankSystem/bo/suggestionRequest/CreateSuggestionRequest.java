package com.fursah.BankSystem.bo.suggestionRequest;
import com.fursah.BankSystem.util.enums.SuggestionStatus;

public class CreateSuggestionRequest {
    private String suggestionText;
    private String rate;
    private SuggestionStatus status;
    public CreateSuggestionRequest(String suggestionText, String rate, SuggestionStatus status) {
        this.suggestionText = suggestionText;
        this.rate = rate;
        this.status = status;
    }



    public String getSuggestionText() {
        return suggestionText;
    }

    public void setSuggestionText(String suggestionText) {
        this.suggestionText = suggestionText;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public SuggestionStatus getStatus() {
        return status;
    }

    public void setStatus(SuggestionStatus status) {
        this.status = status;
    }
}
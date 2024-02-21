package com.fursah.BankSystem.bo.user;

public class UpdateUserRequest {
    private Long userId;
    private String Status;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}

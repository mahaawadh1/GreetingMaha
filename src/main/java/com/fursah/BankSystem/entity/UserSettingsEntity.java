package com.fursah.BankSystem.entity;

import javax.persistence.*;

@Entity
@Table(name ="user_settings")
public class UserSettingsEntity {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean receiveNewsLetter;
    private String preferredLanguage;

    public UserSettingsEntity(Long id, boolean receiveNewsLetter, String preferredLanguage) {
        this.id = id;
        this.receiveNewsLetter = receiveNewsLetter;
        this.preferredLanguage = preferredLanguage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isReceiveNewsLetter() {
        return receiveNewsLetter;
    }

    public void setReceiveNewsLetter(boolean receiveNewsLetter) {
        this.receiveNewsLetter = receiveNewsLetter;
    }

    public String getPreferredLanguage() {
        return preferredLanguage;
    }

    public void setPreferredLanguage(String preferredLanguage) {
        this.preferredLanguage = preferredLanguage;
    }
    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}

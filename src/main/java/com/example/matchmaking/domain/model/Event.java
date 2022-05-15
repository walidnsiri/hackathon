package com.example.matchmaking.domain.model;


import com.example.matchmaking.domain.enums.TypeEvent;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "event") @Data
public class Event {

    @Id
    private ObjectId id;

    private String title;
    private String description;

    private String banner;
    private Inscription inscription;

    private Societe organisateur;
    private String partenaires;

    private TypeEvent typeEvent; //remote, ensite,hybrid
    private String nbr_salle;
    private String nbr_table;


    private boolean enabled;
    private LocalDateTime startDate;
    private LocalDateTime endDate;


    private List<User> participants;
    private List<Session> sessions;

    public boolean isEnabled() {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(startDate) && now.isAfter(endDate);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



}

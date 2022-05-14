package com.example.matchmaking.domain.model;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@Document(collection = "inscription") @Data
public class Inscription {

    @Id
    private ObjectId id;

    private String description;
    private boolean enabled;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public boolean isEnabled() {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(startDate) && now.isAfter(endDate);
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

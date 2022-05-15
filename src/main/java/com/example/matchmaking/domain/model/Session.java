package com.example.matchmaking.domain.model;

import com.example.matchmaking.domain.enums.TypeEvent;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "session") @Data
public class Session {

    private ObjectId id;

    private LocalDateTime date;
    private List<User> participants;
    private String description;
    private TypeEvent typeSession;
    private Event event;
    private List<Meeting> meetings;

}

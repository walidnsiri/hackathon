package com.example.matchmaking.domain.model;


import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Feedbackevent") @Data
public class FeedbackEvent {

    private ObjectId id;

    private Event event;

    private User user;

    private String feedback;

    private String statut;
}

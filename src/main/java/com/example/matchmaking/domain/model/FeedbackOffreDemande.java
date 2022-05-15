package com.example.matchmaking.domain.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "FeedbackOffreDemande") @Data
public class FeedbackOffreDemande {

    private ObjectId id;

    private OffreDemande offreDemande;

    private String feedback;

    private String statut;

}

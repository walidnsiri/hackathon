package com.example.matchmaking.domain.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "offredemande") @Data
public class OffreDemande {

    private ObjectId id;

    private String type;

    private User user;

    private String description;

}

package com.example.matchmaking.domain.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile") @Data
public class Profile {

    @Id
    private ObjectId id;

    private String fullName;
    private String profileImage;
    private String jobTitle;
    private InscriptionRequest inscriptionRequest;
}

package com.example.matchmaking.domain.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inscriptionRequest") @Data
public class InscriptionRequest {

    @Id
    private ObjectId id;
}

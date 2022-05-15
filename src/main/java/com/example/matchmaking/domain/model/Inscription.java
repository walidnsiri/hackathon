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

    private InscriptionRequest inscriptionRequest;




}

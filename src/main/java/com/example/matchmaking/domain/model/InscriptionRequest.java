package com.example.matchmaking.domain.model;

import com.example.matchmaking.domain.enums.Statut;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "inscriptionRequest") @Data
public class InscriptionRequest {

    @Id
    private ObjectId id;

    private Statut statut;
    private String presentationEntreprise;
    private String presentationParticipant;
    private String ActiviteEnreprises;
    private String OffreDemande;
    private String ficheProduits;
    private String services;
    private String solution;
    private String logo;
    private String pdf;
    private String galerieImages;
    private String referenceVideo;
    private String typePartenariat;
    private String sessions;

    private Event event;

    private boolean validated= false;

}

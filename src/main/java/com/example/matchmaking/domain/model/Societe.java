package com.example.matchmaking.domain.model;


import com.example.matchmaking.domain.enums.ActiviteEntreprise;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "society") @Data
public class Societe {

    private ObjectId id;

    private String raisonSociale;
    private String adresse;
    private String ville;
    private String codePostal;
    private String tel;
    private String fax;
    private String email;
    private String siteWeb;
    private LocalDate dateDeCreation;
    private String capitalSocial;
    private ActiviteEntreprise activiteEntreprise;
    private String descriptionActivite;
    private String opportunitePartenariat;
    private String descriptionCooperation;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;

}

package com.example.matchmaking.service;


import com.example.matchmaking.domain.exception.NotFoundException;
import com.example.matchmaking.domain.model.Inscription;
import com.example.matchmaking.domain.model.InscriptionRequest;
import com.example.matchmaking.repository.InscriptionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InscriptionRequestService {

    private final InscriptionRequestRepository inscriptionRequestRepository;


    public InscriptionRequest create(InscriptionRequest inscriptionRequest) {
        return inscriptionRequestRepository.save(inscriptionRequest);
    }

    public InscriptionRequest get(ObjectId id){
        InscriptionRequest inscriptionRequest = inscriptionRequestRepository
                .findById(id).orElseThrow(()-> new NotFoundException("inscription non trouvé: "+ id));

        return inscriptionRequest;
    }

    public List<InscriptionRequest> getAll(){
        return inscriptionRequestRepository.findAll();
    }

    public InscriptionRequest delete(ObjectId id){
        InscriptionRequest inscriptionRequest = inscriptionRequestRepository
                .findById(id).orElseThrow(()-> new NotFoundException("inscription non trouvé: "+ id));
        return inscriptionRequest;
    }
}

package com.example.matchmaking.repository;


import com.example.matchmaking.domain.model.InscriptionRequest;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRequestRepository extends InscriptionRequestCustom, MongoRepository<InscriptionRequest, ObjectId> {
}

interface InscriptionRequestCustom {}

@RequiredArgsConstructor
class InscriptionRequestCustomImpl implements InscriptionRequestCustom {}
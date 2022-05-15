package com.example.matchmaking.repository;


import com.example.matchmaking.domain.model.Inscription;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscriptionRepository extends InscriptionRepositoryCustom, MongoRepository<Inscription, ObjectId> {


}

interface InscriptionRepositoryCustom {}
@RequiredArgsConstructor
class InscriptionRepositoryCustomImpl implements InscriptionRepositoryCustom {}
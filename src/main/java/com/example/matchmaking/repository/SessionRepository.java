package com.example.matchmaking.repository;


import com.example.matchmaking.domain.model.Session;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends SessionRepositoryCustom, MongoRepository<Session, ObjectId> {
}

interface SessionRepositoryCustom {}

@RequiredArgsConstructor
class SessionRepositoryCustomImpl implements SessionRepositoryCustom {}
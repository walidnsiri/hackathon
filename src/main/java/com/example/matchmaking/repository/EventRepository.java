package com.example.matchmaking.repository;


import com.example.matchmaking.domain.model.Event;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends EventRepositoryCustom, MongoRepository<Event, ObjectId> {
}


interface EventRepositoryCustom {}

@RequiredArgsConstructor
class EventRepositoryCustomImpl implements EventRepositoryCustom {}
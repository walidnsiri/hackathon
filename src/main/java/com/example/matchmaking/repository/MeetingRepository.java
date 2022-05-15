package com.example.matchmaking.repository;


import com.example.matchmaking.domain.model.Meeting;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeetingRepository extends MeetingRepositoryCustom, MongoRepository<Meeting, ObjectId> {
}

interface MeetingRepositoryCustom {}

@RequiredArgsConstructor
class MeetingRepositoryCustomImpl implements MeetingRepositoryCustom {}
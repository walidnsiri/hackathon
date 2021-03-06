package com.example.matchmaking.repository;


import com.example.matchmaking.domain.model.Profile;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends ProfileRepositoryCustom, MongoRepository<Profile, ObjectId> {
}


interface ProfileRepositoryCustom {}

@RequiredArgsConstructor
class ProfileRepositoryCustomImpl implements ProfileRepositoryCustom {}
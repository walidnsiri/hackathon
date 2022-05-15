package com.example.matchmaking.repository;


import com.example.matchmaking.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends UserRepositoryCustom, MongoRepository<User, ObjectId> {
    Optional<User> findByUsername(String username);
    Optional<User> findById(ObjectId id);
}

interface UserRepositoryCustom {

   // Page<User> SearchUsers(SearchUsersQuery query, Pageable pageable);
}

@RequiredArgsConstructor
class UserRepositoryCustomImpl implements UserRepositoryCustom {}

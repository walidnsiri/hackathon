package com.example.matchmaking.service;


import com.example.matchmaking.domain.model.Session;
import com.example.matchmaking.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;


    public Session getSession(ObjectId id){
        Optional<Session> sessionData = sessionRepository.findById(id);
        return sessionData.isEmpty()? null : sessionData.get();
    }

    @Transactional
    public Session delete(ObjectId id){
        Optional<Session> sessionData = sessionRepository.findById(id);
        Session session = null;
        if(sessionData.isPresent()){
            session = sessionData.get();
            sessionRepository.delete(session);
        }
        return session;
    }

    @Transactional
    public Session create(Session session) {
        return sessionRepository.save(session);
    }


}

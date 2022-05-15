package com.example.matchmaking.service;


import com.example.matchmaking.domain.model.Event;
import com.example.matchmaking.domain.model.Session;
import com.example.matchmaking.domain.model.User;
import com.example.matchmaking.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    public Event getEvent(ObjectId id) {
        Optional<Event> eventData = eventRepository.findById(id);
        return eventData.orElse(null);
    }
    @Transactional
    public Event delete(ObjectId id){
        Optional<Event> eventData = eventRepository.findById(id);
        Event event = null;
        if(eventData.isPresent()){
            event = eventData.get();
            eventRepository.delete(event);
        }
        return event;
    }

    @Transactional
    public Event create (Event event) {
        return eventRepository.save(event);
    }

    @Transactional
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Transactional
    public Event addParticipantToEvent(Event event,User user){
        Optional<Event> eventData = eventRepository.findById(event.getId());
        if(eventData.isPresent()){
            event = eventData.get();
            List<User> participants = event.getParticipants();
            participants.add(user);
            event.setParticipants( participants);
            eventRepository.save(event);
        }
        return event;
    }


    @Transactional
    public Event addSessionsToEvent(Event event,List<Session> sessions){
        Optional<Event> eventData = eventRepository.findById(event.getId());
        if(eventData.isPresent()){
            event = eventData.get();
            List<Session> currentSessions = event.getSessions();

            sessions.forEach(s -> {
                currentSessions.add(s);
            });
            currentSessions.stream().distinct().collect(Collectors.toList());
            event.setSessions(currentSessions);
            eventRepository.save(event);
        }
        return event;
    }
}

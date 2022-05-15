package com.example.matchmaking.service;


import com.example.matchmaking.domain.model.Event;
import com.example.matchmaking.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}

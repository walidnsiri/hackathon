package com.example.matchmaking.api;


import com.example.matchmaking.domain.model.Event;
import com.example.matchmaking.service.EventService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/event")
@RequiredArgsConstructor
public class EventApi {

    private final EventService eventService;

    @PostMapping("/create")
    public Event create(@RequestBody Event event){
        return eventService.create(event);
    }

    @GetMapping("{id}")
    public Event getEvent(@PathVariable(value = "id") String id){
        return eventService.getEvent(new ObjectId(id));
    }

    @GetMapping("/all")
    public List<Event> getEvents() {
        return eventService.getAll();
    }

    @DeleteMapping("{id}")
    public Event delete(@PathVariable(value="id") String id){
        return eventService.delete(new ObjectId(id));
    }


}

package com.example.matchmaking.api;

import com.example.matchmaking.domain.model.Session;
import com.example.matchmaking.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/session")
@RequiredArgsConstructor
public class SessionApi {

    private final SessionService sessionService;

    @PostMapping("/create")
    public Session create(@RequestBody Session session) {
        return sessionService.create(session);
    }

    @GetMapping("{id}")
    public Session getSession(@PathVariable(value="id") String id) {
        return sessionService.getSession(new ObjectId(id));
    }

   /* @GetMapping("/all")
    public List<Session> getSessions() {
        return sessionService.get
    } */
}

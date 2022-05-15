package com.example.matchmaking.service;

import com.example.matchmaking.domain.model.Meeting;
import com.example.matchmaking.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MeetingService {

    private final MeetingRepository meetingRepository;

   /* public Meeting getMeeting(ObjectId id){

    }*/
}

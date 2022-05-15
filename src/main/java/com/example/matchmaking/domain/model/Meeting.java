package com.example.matchmaking.domain.model;

import com.example.matchmaking.domain.enums.MeetingStatus;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "meeting") @Data
public class Meeting {

    private ObjectId id;

    private User sentRequest;
    private User receivedRequest;


    private MeetingStatus meetingStatus;
    private LocalDateTime date;



}

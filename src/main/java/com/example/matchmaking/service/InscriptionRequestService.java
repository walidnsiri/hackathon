package com.example.matchmaking.service;


import com.example.matchmaking.configuration.util.CookieUtil;
import com.example.matchmaking.configuration.util.JwtTokenUtil;
import com.example.matchmaking.repository.InscriptionRepository;
import com.example.matchmaking.repository.InscriptionRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InscriptionRequestService {

    private final InscriptionRequestRepository inscriptionRequestRepository;
}

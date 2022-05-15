package com.example.matchmaking.api;

import com.example.matchmaking.domain.model.InscriptionRequest;
import com.example.matchmaking.service.InscriptionRequestService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/inscriptionRequest")
@RequiredArgsConstructor
public class InscriptionRequestApi {

    private final InscriptionRequestService inscriptionRequestService;

    @PostMapping("/create")
    public ResponseEntity<InscriptionRequest> create (@RequestBody InscriptionRequest inscriptionRequest){
        inscriptionRequest=inscriptionRequestService.create(inscriptionRequest);
        return ResponseEntity.ok().body(inscriptionRequest);
    }

    @GetMapping("/all")
    public ResponseEntity<List<InscriptionRequest>> getAll(){
        List<InscriptionRequest> inscriptions = inscriptionRequestService.getAll();
        return ResponseEntity.ok().body(inscriptions);
    }

    @PutMapping("/validate/{id}/{validate}")
    public ResponseEntity<InscriptionRequest> validate(@PathVariable(value = "id") String id,
                                                       @PathVariable(value = "validate") String validate){
        InscriptionRequest inscriptionRequest = inscriptionRequestService.validate(new ObjectId(id),Boolean.parseBoolean(validate));
        return ResponseEntity.ok().body(inscriptionRequest);
    }


}

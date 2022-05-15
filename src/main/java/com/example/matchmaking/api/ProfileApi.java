package com.example.matchmaking.api;


import com.example.matchmaking.domain.model.Profile;
import com.example.matchmaking.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/v1/profile")
@RequiredArgsConstructor
public class ProfileApi {

    private final ProfileService profileService;

    @PutMapping("{id}")
    public Profile edit(@PathVariable(value = "id") String id, @RequestBody Profile profile) {
        return profileService.edit(new ObjectId(id),profile);
    }
}

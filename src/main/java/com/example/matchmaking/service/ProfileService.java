package com.example.matchmaking.service;


import com.example.matchmaking.domain.model.Profile;
import com.example.matchmaking.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getProfile(ObjectId id){
        Optional<Profile> profileData = profileRepository.findById(id);
        return profileData.isEmpty()? null : profileData.get();
    }

    public Profile create(Profile profile) {
        profile = profileRepository.save(profile);
        return profile;
    }


    /*

     Profile profile = null;
        if(profileData.isPresent()){
            profile = profileData.get();

        }
    */

}

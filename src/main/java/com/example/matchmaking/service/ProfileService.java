package com.example.matchmaking.service;


import com.example.matchmaking.domain.exception.NotFoundException;
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


    public Profile edit(ObjectId id,Profile profile) {
        Profile updatedProfile = profileRepository.findById(id)
                .orElseThrow(()->new NotFoundException("profile not found"));
        updatedProfile.setFullName(profile.getFullName());
        updatedProfile.setProfileImage(profile.getProfileImage());
        updatedProfile = profileRepository.save(updatedProfile);
        return updatedProfile;
    }


}

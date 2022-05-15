package com.example.matchmaking.service;


import com.example.matchmaking.domain.model.User;
import com.example.matchmaking.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public String encode(String pass){
        return passwordEncoder.encode(pass);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  userRepo
                .findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(format("User with username - %s, not found", username))
                );
    }
    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username).stream().findFirst().orElseThrow(
                () -> new UsernameNotFoundException(format("User with username - %s, not found", username))
        );
    }
    public Optional<User> UserByUsername(String username){
        return userRepo.findByUsername(username);
    }



    public User getUser(ObjectId id) {
        Optional<User> userData = userRepo.findById(id);
        return userData.isEmpty()? null : userData.get();
    }

    @Transactional
    public User delete(ObjectId id) {
        Optional<User> userData = userRepo.findById(id);
        User user = null;
        if(userData.isPresent()){
            user = userData.get();
            user.setEnabled(false);
            user = userRepo.save(user);
        }
        return user;
    }

    @Transactional
    public User create(User user){
      return userRepo.save(user);
    }


    @Transactional
    public User update(ObjectId id, User user) {

        Optional<User> userData = userRepo.findById(id);
        User updatedUser = null;
        if(userData.isPresent()){
            updatedUser = userData.get();
            //sett new values
            updatedUser.setProfile(user.getProfile());
            updatedUser.setPassword(user.getPassword());
            updatedUser.setUsername(user.getUsername());
            //save updated user
            updatedUser = userRepo.save(user);
        }
        return updatedUser;
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }


}

package com.example.matchmaking.api;


import com.example.matchmaking.domain.model.User;
import com.example.matchmaking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/user")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @PostMapping("/create")
    public User create(@RequestBody User user ) {
        return userService.create(user);
    }

    @GetMapping("{id}")
    public User getUser(@PathVariable(value = "id") String id){
        return userService.getUser(new ObjectId(id));
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("{id}")
    public User delete(@PathVariable(value = "id") String id) {
        return userService.delete(new ObjectId(id));
    }

}

package com.coderhouse.controller;

import com.coderhouse.exception.IdNotFoundException;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    @PostMapping("")
    public UserResponse createUser(
            @Validated @RequestBody UserRequest user) {
        return service.create(user);
    }

    @GetMapping("")
    public UserResponse getUser(@RequestBody String id) throws IdNotFoundException {
        return service.getUserById(id);
    }

    @GetMapping("/all")
    public List<UserResponse> getAllUsers() {
        return service.getAll();
    }

}

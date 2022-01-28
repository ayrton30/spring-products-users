package com.coderhouse.service;

import com.coderhouse.exception.IdNotFoundException;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest request);
    UserResponse getUserById(String id) throws IdNotFoundException;
    List<UserResponse> getAll();
}

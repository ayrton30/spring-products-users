package com.coderhouse.service.impl;

import com.coderhouse.builder.UserBuilder;
import com.coderhouse.model.UserFactory;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.repository.UserRepository;
import com.coderhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public UserResponse create(UserRequest request) {
        var entity = UserFactory.createUser(request);
        var entitySaved = repository.save(entity);
        return UserBuilder.entityToResponse(entitySaved);
    }

    @Override
    public List<UserResponse> getAll() {
        return UserBuilder.listEntityToListResponse(repository.findAll());
    }
}

package com.coderhouse.service.impl;

import com.coderhouse.builder.UserBuilder;
import com.coderhouse.cache.CacheClient;
import com.coderhouse.exception.IdNotFoundException;
import com.coderhouse.model.UserFactory;
import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.repository.UserRepository;
import com.coderhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    //redis
    private final CacheClient<UserDocument> cache;
    private final UserRepository repository;


    @Override
    public UserResponse create(UserRequest request) {
        // Los datos se deben persistir en MongoDB y Redis
        var entity = UserFactory.createUser(request);
        var entitySaved = repository.save(entity);
        cache.save(entity.getId().toString(), entity);
        return UserBuilder.entityToResponse(entitySaved);
    }

    @Override
    public UserResponse getUserById(String id) throws IdNotFoundException {
        //Los datos al obtenerse por id primero deben buscarse en Redis y en caso de no existir
        // en ese repositorio, buscarlo en MongoDB y persistirlo en Redis para la próxima búsqueda.

        var dataFromCache = cache.recover(id, UserDocument.class);
        //si encuentra el objeto en cache
        if (!Objects.isNull(dataFromCache)) {
            return UserBuilder.entityToResponse(dataFromCache);
        }

        var entity = repository.findById(id).orElseThrow(() -> new IdNotFoundException());
        cache.save(entity.getId(), entity);
        return UserBuilder.entityToResponse(entity);
    }

    @Override
    public List<UserResponse> getAll() {
        return UserBuilder.listEntityToListResponse(repository.findAll());
    }
}

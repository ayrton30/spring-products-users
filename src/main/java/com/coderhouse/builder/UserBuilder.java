package com.coderhouse.builder;

import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.database.document.concrete.user.UserAdmin;
import com.coderhouse.model.database.document.concrete.user.UserClient;
import com.coderhouse.model.database.document.concrete.user.UserEditor;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserBuilder {

    //creación de los distintos usuarios a partir de un UserRequest
    //recibido del request de la REST Api
    //UserRequest->Controller->Service->UserFactory->UserBuilder

    public static UserAdmin requestToEntityAdmin(UserRequest request) {
        return UserAdmin.builder()
                .type(request.getType())
                .username(request.getUsername())
                .password(request.getPassword())
                .createDate(LocalDateTime.now())
                .admin(request.getAdmin())
                .build();
    }

    public static UserEditor requestToEntityEditor(UserRequest request) {
        return UserEditor.builder()
                .type(request.getType())
                .username(request.getUsername())
                .password(request.getPassword())
                .createDate(LocalDateTime.now())
                .editor(request.getEditor())
                .build();
    }

    public static UserClient requestToEntityClient(UserRequest request) {
        return UserClient.builder()
                .type(request.getType())
                .username(request.getUsername())
                .password(request.getPassword())
                .createDate(LocalDateTime.now())
                .client(request.getClient())
                .build();
    }

    //el builder también es el encargado de mapear de un documento
    //entidad de mongoDB a una respuesta que dara la Api

    public static <T extends UserDocument> UserResponse entityToResponse(T entity) {
        var user = UserResponse.builder()
                .code(entity.getId())
                .createDate(entity.getCreateDate())
                .type(entity.getType())
                .username(entity.getUsername())
                .build();

        switch (entity.getType()) {
            case "admin":
                user.setAdmin(((UserAdmin) entity).getAdmin());
                return user;
            case "editor":
                user.setEditor(((UserEditor) entity).getEditor());
                return user;
            case "client":
                user.setClient(((UserClient) entity).getClient());
                return user;
            default:
                return null;
        }
    }

    public static List<UserResponse> listEntityToListResponse(List<UserDocument> users) {
        var listResponse = new ArrayList<UserResponse>();
        users.forEach(userItem -> listResponse.add(entityToResponse(userItem)));
        return listResponse;
    }

}

package com.coderhouse.model;

import com.coderhouse.builder.UserBuilder;
import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.request.UserRequest;
import lombok.Data;

@Data
public class UserFactory {

    public static UserDocument createUser(UserRequest request) {
        switch (request.getType()) {
            case "admin":
                return UserBuilder.requestToEntityAdmin(request);
            case "editor":
                return UserBuilder.requestToEntityEditor(request);
            case "client":
                return UserBuilder.requestToEntityClient(request);
            default:
                return null;
        }
    }
}

package com.coderhouse.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank(message = "El campo type no puede ser vacío")
    @Pattern(regexp = "^(admin|editor|client)$", message = "Se acepta: {admin}, {editor}, {client}")
    private String type;
    @NotBlank(message = "El campo username no puede ser vacío")
    private String username;
    @NotBlank(message = "El campo password no puede ser vacío")
    private String password;

    private String admin;
    private String client;
    private String editor;



}

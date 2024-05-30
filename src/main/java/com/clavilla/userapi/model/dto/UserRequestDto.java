package com.clavilla.userapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

    @JsonProperty("name")
    @NotBlank
    private String name;

    @JsonProperty("email")
    @NotBlank
    private String email;

    @JsonProperty("password")
    @NotBlank
    private String password;

    @JsonProperty("phones")
    @NotEmpty
    @Valid
    private List<PhoneDto> phones;
}

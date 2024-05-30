package com.clavilla.userapi.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(hidden = true)
public class PhoneDto {

    @JsonProperty("number")
    private String number;

    @JsonProperty("citycode")
    private String citycode;

    @JsonProperty("contrycode")
    private String contrycode;
}

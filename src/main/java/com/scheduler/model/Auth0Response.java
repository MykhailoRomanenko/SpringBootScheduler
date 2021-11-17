package com.scheduler.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
public class Auth0Response {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("id_token")
    private String idToken;

    private String scope;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("token_type")
    private String tokenType;
}
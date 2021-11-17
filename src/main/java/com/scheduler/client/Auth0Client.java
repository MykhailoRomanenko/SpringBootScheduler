package com.scheduler.client;

import com.scheduler.exception.UnauthorizedException;
import com.scheduler.model.Auth0Response;
import com.scheduler.model.Auth0Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Auth0Client {

    @Value("${auth0.client-id}")
    private String clientId;

    @Value("${auth0.client-secret}")
    private String clientSecret;

    @Value("${auth0.endpoint}")
    private String endpoint;

    @Value("${auth0.redirect-url}")
    private String redirectUrl;

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;


    private final RestTemplate restTemplate;

    public Auth0Client(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAccessToken(String code) {
        Auth0Request clientAuthRequest = new Auth0Request();
        clientAuthRequest.setClientId(clientId);
        clientAuthRequest.setClientSecret(clientSecret);
        clientAuthRequest.setCode(code);
        clientAuthRequest.setGrantType("authorization_code");
        clientAuthRequest.setRedirectUri(redirectUrl);
        ResponseEntity<Auth0Response> response;
        try {
            response = restTemplate
                    .postForEntity(endpoint, clientAuthRequest, Auth0Response.class);
        } catch (Exception e) {
            throw new UnauthorizedException("Exception during authorization", e);
        }
        return response.getBody().getAccessToken();
    }

    public String formAuthorizationUrl() {
        return issuer + "authorize" +
                "?response_type=code&client_id=" + clientId +
                "&audience=" + audience +
                "&redirect_uri=" + redirectUrl +
                "&scope=openid profile email";
    }
}

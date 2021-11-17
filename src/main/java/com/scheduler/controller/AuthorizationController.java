package com.scheduler.controller;

import com.scheduler.client.Auth0Client;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin(origins = "*")
public class AuthorizationController {

    private final Auth0Client auth0Client;

    public AuthorizationController(Auth0Client auth0Client) {
        this.auth0Client = auth0Client;
    }

    @GetMapping("/auth/token")
    public String findById(@RequestParam("code") String code) {
        return auth0Client.getAccessToken(code);
    }
}

package com.scheduler.controller;

import com.scheduler.client.Auth0Client;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping(path = "/")
public class HomeController {

    private final Auth0Client auth0Client;

    public HomeController(Auth0Client auth0Client) {
        this.auth0Client = auth0Client;
    }

//    @GetMapping(value = "/")
//    public RedirectView home() {
//        return new RedirectView(auth0Client.formAuthorizationUrl());
//    }

    @GetMapping(value = "/test")
    public String test(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
}
package com.orange.oab.beerwebapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BeerController {

    private final ObjectMapper objectMapper;
    private final KeycloakRestTemplate keycloakRestTemplate;

    public BeerController(ObjectMapper objectMapper, KeycloakRestTemplate keycloakRestTemplate) {
        this.objectMapper = objectMapper;
        this.keycloakRestTemplate = keycloakRestTemplate;
    }

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/beers")
    public String about(Model model) throws IOException {
        ResponseEntity<String> response = keycloakRestTemplate.getForEntity("http://localhost:8082/rest/beers", String.class);
        List<Map<String, Object>> beers = objectMapper.readValue(response.getBody(), new TypeReference<ArrayList<HashMap<String, String>>>() {});
        model.addAttribute("beers", beers);
        return "beers";
    }

}


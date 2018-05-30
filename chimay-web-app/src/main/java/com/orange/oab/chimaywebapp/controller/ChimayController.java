package com.orange.oab.chimaywebapp.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChimayController {

    private final ObjectMapper objectMapper;

    public ChimayController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @RequestMapping(value = "/")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/beers")
    public String about(Model model) throws IOException {
        List<Map<String, Object>> beers = objectMapper.readValue(
                getClass().getResourceAsStream("/static/beers/beers.json"),
                new TypeReference<ArrayList<HashMap<String, String>>>() {
                });
        model.addAttribute("beers", beers);
        return "beers";
    }

}


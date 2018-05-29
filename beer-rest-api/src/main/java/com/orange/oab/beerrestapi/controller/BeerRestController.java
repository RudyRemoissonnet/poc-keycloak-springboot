package com.orange.oab.beerrestapi.controller;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class BeerRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/rest/beers")
    public String beers() throws IOException {
        logger.info("returning beers list");
        return IOUtils.toString(getClass().getResourceAsStream("/static/beers.json"), Charset.defaultCharset());
    }
}

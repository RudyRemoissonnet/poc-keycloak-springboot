package com.orange.oab.beerrestapi.controller;

import org.apache.commons.io.IOUtils;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.OidcKeycloakAccount;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.Principal;

@RestController
public class BeerRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/rest/beers")
    public String beers(HttpServletRequest request) throws IOException {

        Principal userPrincipal = request.getUserPrincipal();
        if (userPrincipal instanceof KeycloakAuthenticationToken) {
            logUserInfo((KeycloakAuthenticationToken) userPrincipal);
        }

        return IOUtils.toString(getClass().getResourceAsStream("/static/beers.json"), Charset.defaultCharset());
    }

    private void logUserInfo(KeycloakAuthenticationToken token) {
        OidcKeycloakAccount account = token.getAccount();
        KeycloakSecurityContext securityContext = account.getKeycloakSecurityContext();
        logger.info("user name : {}", token.getName());
        logger.info("user id : {}", securityContext.getToken().getSubject());
        logger.info("user roles: {}", account.getRoles());
        logger.info("access token : {}", securityContext.getTokenString());
    }
}

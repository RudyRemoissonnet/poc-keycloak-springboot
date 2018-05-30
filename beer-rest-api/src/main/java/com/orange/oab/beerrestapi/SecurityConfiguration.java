package com.orange.oab.beerrestapi;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.keycloak.adapters.springsecurity.authentication.KeycloakAuthenticationProvider;
import org.keycloak.adapters.springsecurity.config.KeycloakWebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.web.authentication.session.NullAuthenticatedSessionStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;

@KeycloakConfiguration
class SecurityConfiguration extends KeycloakWebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
        // Specifies the session authentication strategy
        return new NullAuthenticatedSessionStrategy();
    }

    @Bean
    public KeycloakSpringBootConfigResolver KeycloakConfigResolver() {
        // this defines that we want to use the Spring Boot properties file support instead of the default keycloak.json
        return new KeycloakSpringBootConfigResolver();
    }

    @Autowired
    public void securityProvider(AuthenticationManagerBuilder auth) {
        // Submits the KeycloakAuthenticationProvider to the AuthenticationManager
        // tasks the SimpleAuthorityMapper to make sure roles are not prefixed with ROLE_
        KeycloakAuthenticationProvider keycloakAuthenticationProvider = keycloakAuthenticationProvider();
        keycloakAuthenticationProvider.setGrantedAuthoritiesMapper(new SimpleAuthorityMapper());
        auth.authenticationProvider(keycloakAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().antMatchers("/rest/beers").hasRole("beer_lover");
    }

}

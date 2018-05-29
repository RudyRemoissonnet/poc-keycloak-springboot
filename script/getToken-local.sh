#!/usr/bin/env bash

# default client created in keycloak
# - 'Access type' is set to 'public'
curl -X POST "http://localhost:8080/auth/realms/oab/protocol/openid-connect/token" \
     -d "client_id=beer-web" \
     -d "username=rudy" \
     -d "password=rudy" \
     -d "grant_type=password" \
     | jq .
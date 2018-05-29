#!/bin/bash

export TKN=$(curl -X POST "http://localhost:8080/auth/realms/oab/protocol/openid-connect/token" \
                  -H "Content-Type: application/x-www-form-urlencoded" \
                  -d "client_id=beer-api-client" \
                  -d "username=rudy" \
                  -d "password=rudy" \
                  -d "grant_type=password" \
                  -d "client_secret=1a8d84cf-97fe-4216-be7c-4769a1c8cd53" \
                  | jq -r ".access_token")

curl -X GET "http://localhost:8082/rest/beers" \
     -H "Accept: application/json" \
     -H "Authorization: Bearer $TKN" \
     | jq .
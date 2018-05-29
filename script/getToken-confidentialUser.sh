#!/usr/bin/env bash

# client must be configured to :
# - have 'Access type' to 'confidential' in order to have new tab credential
# - only option 'Direct Access Grands Enabled' switch on
# user must be configured with :
# - 'Email verified' switch on
# - 'Temporary' from credential tab switch off
curl -X POST "http://localhost:8080/auth/realms/oab/protocol/openid-connect/token" \
     -H "Content-Type: application/x-www-form-urlencoded" \
     -d "client_id=beer-api-client" \
     -d "username=rudy" \
     -d "password=rudy" \
     -d "grant_type=password" \
     -d "client_secret=1a8d84cf-97fe-4216-be7c-4769a1c8cd53" \
     | jq .
Feature: Verify End to End Scenario

  Scenario: Verify user can create, update and delete the user
    Given user wants to call "/users" end point
    And set header "Content-Type" to "application/json"
    And set header "Accept" to "application/json"
    And set header "Authorization" to "@token.value"
    And set request body from file "create_user.json"
    When user performs post call
    Then verify status code 201
    And store "id" into "user.id"




Feature: Create patient invalid

  Scenario: User wants to create patient without passing parameters
    Given User performs post without form data
    When User tries Post requests without form data
    Then Will receive an error code 400

  Scenario: Create patient without access token
    Given user tries to create patient without access token
    When user does post request without token
    Then user will get 401 unauthorized

Feature: Create patient invalid

  Scenario: User wants to create patient without passing parameters
    Given User performs post without form data
    When User tries Post requests without form data
    Then Will receive an error code 400 
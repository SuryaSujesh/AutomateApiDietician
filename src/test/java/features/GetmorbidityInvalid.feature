Feature: Morbidity invalid

  Scenario: User tries to get morbidity details without access
    Given User performs get morbidity without access token
    When User tries GET http requests without access token
    Then User will get 401 error code 
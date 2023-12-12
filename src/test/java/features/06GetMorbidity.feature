Feature: Get morbidity details

  Scenario: User gets the morbidity details
    Given User is logged in with valid credentials
    When User performs GET http requests
    Then User get morbidity details with 200OK

  Scenario: User gets morbidity condition by testname
    Given User is logged in with valid credentials
    When User performs GET http requests with morbidityname
    Then User get morbidity condition with 200OK

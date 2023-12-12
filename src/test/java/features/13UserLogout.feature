Feature: User logout

  Scenario: User tries to logout
    Given User is logged in with username and password
    When User performs GET method to logout
    Then User successfully logout with status code 200

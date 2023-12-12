Feature: Patient logout

  Scenario: Patient tries to logout
    Given Patient tries to logout with endpoint
    When Patient performs GET method to logout
    Then Patient successfully logout with success meassage and status code 200

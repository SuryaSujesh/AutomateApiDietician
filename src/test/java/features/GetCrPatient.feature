Feature: Get patient details

  Scenario: User gets the patient details
    Given User is logged in and tries to get all patient details
    When User performs GET http requests for getting patient details
    Then User will receive all the patient details with status code 200


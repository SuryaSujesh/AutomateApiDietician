Feature: User is able to create a patient

  Scenario: User is able to create a patient
    Given User enters patient details 
    When User performs POST http requests to create a patient
    Then User receives status code 201

 Scenario: User is able to update a patient
    Given User tries to update patient details for allergy and phone number
    When User performs PUT http requests to update a patient
    Then User receives status code with updated message and status code 200

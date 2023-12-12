Feature: User wants to delete a patient

  Scenario: User wants to delete a patient
    Given User tried to delete a patient account
    When User performed http method to delete a patient
    Then User will get a success message with status code 200

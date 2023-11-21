Feature: Invalid Scenario

 Scenario: User wants to delete a patient that does not exists
  Given User tried to delete a patient account that does not exists
  When User tried Delete http requests
  Then User will get a error code with status 404

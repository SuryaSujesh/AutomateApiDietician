Feature: Update an existing patient with same data

  Scenario: User is able to update an existing patient with same data
   Given User tries to update patient with same phone number and DateOfBirth
   When User tries to update an existing patient with PUT requests
   Then User will receive an error message with 400

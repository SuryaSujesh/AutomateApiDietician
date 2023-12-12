Feature: Patient try to login

  Scenario Outline: Patient tries to login with invalid credentials
    Given Patient login endoint is given with "<testcaseName>" and "<Sheetname>"
    When Patient tries to login with wrong credential
    Then Status code 401 is created is created an error message

    Examples: 
      | testcaseName | Sheetname    |
      | Case     | Invalid |
          
   
  Scenario Outline: Patient is able to login
    Given Patient login endpoint is given with "<testcaseName>" and "<Sheetname>"
    When Patient tries to login with Post http request
    Then An access token is created for the patient
    And Patient receives status code 200

    Examples: 
      | testcaseName | Sheetname |
      |  LoginData     | PatLogin     |

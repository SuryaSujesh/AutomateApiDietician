Feature: User able to login and create an access token

  Scenario Outline: User is able to login
    Given Login API endpoint is given with "<testcaseName>" and "<Sheetname>"
    When User tried to login with post http method
    Then Access token is generated
    And Receives status code 200

    Examples: 
      | testcaseName | Sheetname |
      | Positive     | Login     |

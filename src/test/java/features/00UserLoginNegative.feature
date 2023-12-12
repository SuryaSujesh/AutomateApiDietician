Feature: User tried to login with wrong credential

  Scenario Outline: User is not able to login
    Given Login API endpoint is given with "<testcaseName>" and "<Sheetname>"
    When Tries to login
    Then Invalid login and status code 401 is created

    Examples: 
      | testcaseName | Sheetname |
      | Case         | Invalid   |

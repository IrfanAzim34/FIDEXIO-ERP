Feature: LoginFunctionality

  Background: User on login page
    Given user on the login page


  Scenario Outline: User can login with valid credentials
    When user enter username "<username>" in username box
    And user enter password "<password>" in password box
    And user click login button
    Then user should login homepage
    Examples:
      | username                | password     |
      | salesmanager15@info.com | salesmanager |
      | posmanager20@info.com   | posmanager   |


  Scenario Outline: User can not login with invalid credentials
    When user enter username "<username>" in username box
    And user enter password "<password>" in password box
    And user click login button
    Then user should see wrong credentials message
    Examples:
      | username                 | password     |
      | salesmanager100@info.com | abscd        |
      | abcd@info.com            | salesmanager |

  @wip
  Scenario Outline: User should see validation message when using empty credentials
    When user enter username "<username>" in username box
    And user enter password "<password>" in password box
    And user click login button
    Then user should see "Please fill out this field." message
    Examples:
      | username                | password    |
      |                         |             |
      | salesmanager39@info.com |             |
      |                         | posmanager  |
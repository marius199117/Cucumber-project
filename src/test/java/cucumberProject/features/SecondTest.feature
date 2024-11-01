Feature: Second Test

  @Regression
  Scenario: Second Test
    Given I Open The Browser
    And I Navigate To "https://www.saucedemo.com" Page
    Then I Successfully Login With Valid Username "problem_user" And Valid Password "secret_sauce"

  @Regression
  Scenario: Close browser
    Given I Close The Browser
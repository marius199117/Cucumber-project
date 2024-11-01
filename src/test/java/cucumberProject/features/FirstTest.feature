Feature: First Test

  @Smoke @Regression
  Scenario: First Test
    Given I Open The Browser
    And I Navigate To "https://www.saucedemo.com" Page
    Then I Successfully Login With Valid Username "standard_user" And Valid Password "secret_sauce"

  @Smoke @Regression
  Scenario: Close browser
    Given I Close The Browser
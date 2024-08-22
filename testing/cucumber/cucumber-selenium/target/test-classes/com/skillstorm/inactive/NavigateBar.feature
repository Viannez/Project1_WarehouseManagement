@navigate
Feature: Use navigdation bar

    Scenario Outline: Successfully add product
    Given I am on the warehouse url
    When I click on the navigate button "<navigate>"
    Then I be on the "<navigate>" page

  Examples:
    |navigate     |
    |Products     |
    |Warehouses   |
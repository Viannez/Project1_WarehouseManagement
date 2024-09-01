@go-to-warehouse
Feature: CLick on warehouse details of warehouse card
    Scenario Outline: Successfully get to warehouse details page
    Given I am on the warehouse page with cards
    When I click on the details button for warehouse card "<name>"
    Then I be on the "<name>" page

  Examples:
    |name       |
    |testName   |
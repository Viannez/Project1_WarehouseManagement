@go-to-product
Feature: CLick on product details of product card
    Scenario Outline: Successfully get to product details page
    Given I am on the product page with cards
    When I click on the details button for product card "<name>"
    Then I be on the "<name>" page

  Examples:
    |name       |
    |testName   |
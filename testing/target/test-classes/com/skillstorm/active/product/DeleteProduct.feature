@delete-product @phase3
Feature: Delete product from the products page
    
  Scenario Outline: Click on product card delete button and see the card removed
    Given I am on the products page that has product cards
    And There exists a product card named "<productName>"
    When I click the delete button on the product card named "<productName>"
    Then I should no longer see a card named "<productName>" on product page

    Examples:
      |productName        |
      |updatedProduct1    |
      |updatedProduct2    |
      |updatedProduct3    |
@delete-product
Feature: Delete product from the products page

  Scenario: Click on product card delete button and see card removed
    Given I am on the products page that has product cards
    And There exists a product card named "testName2"
    When I click the delete button on the product card named "testName2"
    Then I should no longer see a card named "testName2" on product page

@delete-warehouse
Feature: Delete warehouse from the warehouses page

  Scenario: Click on warehouse card delete button and see card removed
    Given I am on the warehouses page that has warehouse cards
    And There exists a warehouse card named "testName2"
    When I click the delete button on the warehouse card named "testName2"
    Then I should no longer see a card named "testName2" on warehouse page
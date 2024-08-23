@delete-warehouse
Feature: Delete warehouse from the warehouses page

  Scenario Outline: Click on warehouse card delete button and see card removed
    Given I am on the warehouses page that has warehouse cards
    And There exists a warehouse card named "<warehouseName>"
    When I click the delete button on the warehouse card named "<warehouseName>"
    Then I should no longer see a card named "<warehouseName>" on warehouse page

    Examples:
      |warehouseName  |
      |testName     |
      |testName2    |
    
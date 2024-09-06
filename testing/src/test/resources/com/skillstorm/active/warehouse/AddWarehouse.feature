@add-warehouse @phase1
Feature: Add a warehouse to the warehouses page

  Scenario: Click on add warehouse button
    Given I am on the warehouses page
    When I click on the add warehouse button
    Then I should see the add warehouse form modal

  Scenario Outline: Successfully add warehouse
    Given I have warehouse modal open
    When I enter valid "<warehouseName>" and "<warehouseAddress>" and "<capacity>"
    And I click the warehouse form submit button
    Then I should see warehouse a card with matching "<warehouseName>" and "<warehouseAddress>" and "<capacity>"

  Examples:
    |warehouseName  |warehouseAddress   |capacity  |
    |testName       |Orlando, Florida   |500       |
    |testName2      |Atlanta, Georgia   |250       |
    |testName3      |Las Vegas, Nevada  |1000      |
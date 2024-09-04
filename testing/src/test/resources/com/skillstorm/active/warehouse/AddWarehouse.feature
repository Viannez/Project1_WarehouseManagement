@add-warehouse
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
    |testName       |Orlando, FLorida   |500       |
    |testName2      |Atlanta, Georgia   |250       |

# add invalid add warehouse name scenario
# Scenario: Unsuccessfully add warehouse because of name
#     Given I have warehouse modal open
#     When I enter invalid "testName" and "testAddress" and "<400>"
#     And I click the submit button
#     Then I should see a message "Warehouse name already taken."

# add invalid add warehouse capacity scenario
# Scenario: Unsuccessfully add warehouse because of capacity
#     Given I have warehouse modal open
#     When I enter invalid "testName" and "testAddress" and "<10>"
#     And I click the submit button
#     Then I should see a message "Check inputs."

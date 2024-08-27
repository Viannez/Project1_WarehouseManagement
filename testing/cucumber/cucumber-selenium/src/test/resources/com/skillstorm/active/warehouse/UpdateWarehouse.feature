@update-warehouse
Feature: Update warehouse

  Scenario: Click on update warehouse button
    Given I am on the warehouse details page
    When I click on the update warehouse button
    Then I should see the update warehouse form modal

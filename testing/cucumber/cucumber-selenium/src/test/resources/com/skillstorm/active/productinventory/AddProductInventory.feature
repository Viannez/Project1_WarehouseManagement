@add-productinventory
Feature: Add a product to a warehouse

    Scenario: Click on add product inventory button
        Given ProductInventory: I am on the warehouse details page
        When I click on the add product to warehouse button
        Then I should see the add product to warehouse form modal
@add-productinventory @phase1
Feature: Add a product to a warehouse

    Scenario: Click on add product inventory button
        Given ProductInventory: I am on the warehouse "Electronics Galore" details page
        When I click on the add product to warehouse button
        Then I should see the add product to warehouse form modal

    Scenario: Successfully add a warehouse product inventory
        Given I have the add product to warehouse "Electronics Galore" form modal open
        When I enter valid product fields with name = "<productName>" and stock = "<productStock>"
        And I click the add product to warehouse submit button
        Then I should see the warehouse's newly added product in stock with name = "<productName>" and stock = "<productStock>"

    Examples:
        |productName              |productStock        |
        |miscellaneous            |35                  |
        |dishware                 |11                  |
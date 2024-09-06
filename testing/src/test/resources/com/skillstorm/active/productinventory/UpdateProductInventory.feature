@update-productinventory
Feature: Update product inventory on warehouse page

    Scenario: Click on update product inventory button
        Given Update Product Inventory: I am on the warehouse "Electronics Galore" details page
        When I click on the update product inventory button 
        Then I should see the update product inventory form modal

    Scenario: Successfully update a product inventory
        Given I have the update product inventory "<productInventoryName>" form modal open
        When I enter valid update fields with stock = "<updatedStock>"
        And I click the update product inventory form submit button
        Then I should see the product inventory "<productInventoryName>" updated with stock = "<updatedStock>"

    Examples:
        |productInventoryName     |updatedStock      |
        |miscellaneous            |1                 |
        |new books                |4                 |
        |supplies                 |76                |
        |electronics              |12         `      |
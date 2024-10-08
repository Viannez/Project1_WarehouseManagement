@delete-productinventory @phase3
Feature: Delete a product from a warehouse

    Scenario: Click on delete product button and see the product removed from the warehouse
        Given Delete ProductInventory: I am on the warehouse details page
        And there exists a product in the warehouse named "<productName>"
        When I click on the delete product from warehouse button for the product named "<productName>"
        Then I should no longer see a product named "<productName>" for that warehouse

    Examples:
        |productName   |
        |miscellaneous |
        |new books     |
        |supplies      |
        |electronics   |
@update-product
Feature: Update product

    Scenario: Click on update product button
        Given I am on the product details page
        When I click on the update product button
        Then I should see the update product form modal

    Scenario: Successfully update a product
        Given I have the update product form modal open
        When I enter valid update fields with name = "<updatedName>", category = "<updatedCategory>", price = "<updatedPrice>"
        And I click the update product form submit button
        Then I should see the product updated with name = "<updatedName>", category = "<updatedCategory>", price = "<updatedPrice>"

    Examples:
        |updatedName          |updatedCategory |updatedPrice  |
        |updatedProductName1  |L               |200           |
        |updatedProductName2  |S               |300           |
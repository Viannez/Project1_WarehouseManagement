@update-product @phase2
Feature: Update product

    Scenario: Click on update product button
        Given I am on the product "supplies" details page
        When I click on the update product button 
        Then I should see the update product form modal

    Scenario: Successfully update a product
        Given I have the update product "<productName>" form modal open
        When I enter valid update fields with name = "<updatedName>", category = "<updatedCategory>", price = "<updatedPrice>"
        And I click the update product form submit button
        Then I should see the product updated with name = "<updatedName>", category = "<updatedCategory>", price = "<updatedPrice>"

    Examples:
        |productName        |updatedName      |updatedCategory |updatedPrice  |
        |testName           |updatedProduct1  |L               |200           |
        |testName2          |updatedProduct2  |S               |300           |
@update-product
Feature: Update product

    Scenario: Click on update product button
        Given I am on the product details page
        When I click on the update product button
        Then I should see the update product form modal
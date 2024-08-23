@sort-product
Feature: Sort product on the products page

    Scenario: Select sort by ID
        Given I am on the products page that is unsorted
        And There is more than one product card
        When I select the option with text "By ID"
        Then The product list on product page should be ordered by ID

    Scenario: Select sort by Price
        Given I am on the products page that is unsorted
        And There is more than one product card
        When I select the option with text "By Price"
        Then The product list on product page should be ordered by Price

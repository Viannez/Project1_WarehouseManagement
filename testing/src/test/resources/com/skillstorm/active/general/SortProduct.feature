@sort-product @phase1
Feature: Sort product on the products page

    Scenario: Select option to sort product on ProductPage
        Given I am on the products page with unsorted products
        When I select the option with text "<selectOption>"
        Then The product list on product page should be ordered by "<selectOption>"
    Examples:
            |selectOption       |
            |By Price           |
            |By ID              |
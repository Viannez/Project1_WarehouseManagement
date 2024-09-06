@sort-warehouse @ohase1
Feature: Sort warehouses on WarehousePage

    Scenario: Select option to sort warehouses on WarehousePage
        Given I am on the warehouses page
        When I select the sort warehouse option with text "<selectOption>"
        Then The warehouse list on warehouse page should be ordered by "<selectOption>"
    Examples:
            |selectOption      |
            |By Capacity        |
            |By ID              |
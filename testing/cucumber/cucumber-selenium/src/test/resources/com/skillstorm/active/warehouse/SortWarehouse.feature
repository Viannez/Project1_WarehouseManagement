@sort-warehouse
Feature: Sort warehouse on the warehouses page
 
    Scenario: Select sort by ID
        Given I am on the warehouses page that is unsorted
        And There is more than one warehouse card
        When I select the option with text "By ID"
        Then The warehouse list on the warehouses page should be ordered by ID
 
    Scenario: Select sort by Capacity
        Given I am on the warehouses page that is unsorted
        And There is more than one warehouse card
        When I select the option with text "By Capacity"
        Then The warehouse list on the warehouses page should be ordered by Capacity
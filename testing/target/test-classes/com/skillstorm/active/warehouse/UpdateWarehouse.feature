@update-warehouse
Feature: Update warehouse

    Scenario: Click on update warehouse button
        Given I am on the warehouse details page
        When I click on the update warehouse button
        Then I should see the update warehouse form modal

    Scenario: Successfully update a warehouse
        Given I have the update warehouse form modal open
        When I enter valid update fields with name = "<updatedName>", address = "<updatedAddress>", capacity = "<updatedCapacity>"
        And I click the update warehouse form submit button
        Then I should see the warehouse updated with name = "<updatedName>", address = "<updatedAddress>", capacity = "<updatedCapacity>"

    Examples:
        |updatedName            |updatedAddress        |updatedCapacity  |
        |updatedWarehouse1      |Baltimore, Maryland   |200              |
        |updatedWarehouse2      |Columbia, Maryland    |300              |
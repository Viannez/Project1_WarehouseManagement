@navigate @phase2
Feature: Use navigdation bar

    Scenario: Navigate from firstPage to secondPage
    Given I am currently on the "<firstPage>"
    When I click on the correct button for the "<secondPage>"
    Then I be on the "<secondPage>" page
    Examples:
      |firstPage           |secondPage |
      |Products            |Warehouses |
      |Warehouses          |Products   |
      # |Warehouse           |Warehouses |
      # |Warehouse           |Products   |
      # |Product             |Warehouses |
      # |Product             |Products   |
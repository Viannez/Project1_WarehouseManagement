@add-product @phase1
Feature: Add a product to the products page

  Scenario: Click on add product button
    Given I am on the products page
    When I click on the add product button
    Then I should see the add product form modal

  Scenario Outline: Successfully add product
    Given I have product modal open
    When I enter valid "<productName>" and "<productPrice>" and select "<category>"
    And I click the submit button
    Then I should see a card with matching "<productName>" and "<productPrice>" and "<category>"

  Examples:
    |productName  |productPrice  |category  |
    |testName     |100           |M         |
    |testName2    |300           |XL        |

# add invalid 
# Scenario: Unsuccessfully add product 
#     Given I have product modal open
#     When I enter invalid <productName and "40" and select "<S>"
#     And I click the submit button
#     Then I should see a message "Product name already taken."
#  Examples:
#     |productName  |productPrice  |category  |
#     |knicknacks   |0             |M         |
#     |normaladd    |-1            |XL        |

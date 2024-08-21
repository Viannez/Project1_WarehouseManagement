@add-product
Feature: Add a product to the products page

  Scenario: Click on add product button
    Given I am on the products page
    When I click on the add product button
    Then I should see the add product form modal

  Scenario Outline: Successfully add product
    Given I have product modal open
    When I enter valid "<productName>" and "<productPrice>" and select "<categoryID>"
    And I click the submit button
    # Then I should see a card with matching "<productName>" and "<productPrice>" and "<categoryID>"

  Examples:
    |productName  |productPrice  |categoryID|
    |testName     |100           |2         |
    |testName2    |300           |4         |

  # Scenario: Unsuccessful login with invalid credentials
  # Given I am on the login page
  # When I enter invalid "username" and "password"
  # And I click the login button
  # Then I should see an error message "Login Failed"


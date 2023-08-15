Feature: All Screen Feature
  This feature is to check some of functionallity

  @Smoke
  Scenario: As a customer, i want to checkout my cart
    Given I see the login form
    And I enter email in login email profiling
    And I enter password in login email profiling
    And I click log in button
    Then I should see Products page with title
    When the product is listed with title "<title>" and price "<price>"
    And I click add to cart button
    And I click cart button
    Then I should see product list
    And I click Checkout Button
    Then I should see Checkout page with title
    And I enter first name
    And I enter last name
    And I enter zip code
    And I click continue button
    And I click finish button
    Then I should see Checkout Complete

    Examples:
    | title                   | price  |
    | Sauce Labs Backpack     | $29.99 |



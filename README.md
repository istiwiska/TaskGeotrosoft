# Task Geotrosoft Automation Mobile
This an project for Pintu using Appium and cucumber for Java

# Important
Make sure you have JAVA 11 or above installed to run this test

There is the step : 
Scenario Outline: As a customer, i want to checkout my cart
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
      | title               | price  |
      | Sauce Labs Backpack | $29.99 |

and to update udid or profile User you can update in config.properties

to run this project you can run task.feature
[image](https://github.com/istiwiska/TaskGeotrosoft/assets/22950110/df3c1474-b5cd-4d8b-80d5-ba106661fb71)


below i attached the video recording
[https://github.com/istiwiska/TaskGeotrosoft/assets/22950110/8c57c6e5-6aa5-4273-8e01-27deb86def8a](https://drive.google.com/file/d/1Qt87ELItwKAPveTo1XTpdlo3bavxakr9/view?usp=sharing)

report : 
[image](https://github.com/istiwiska/TaskGeotrosoft/assets/22950110/1b073456-c4d4-4bee-9056-a56bf33d7a1a)

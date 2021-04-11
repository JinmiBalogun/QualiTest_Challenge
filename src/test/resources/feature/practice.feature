Feature: This feature file tests the functionality of Automation Practice app

  Background: Common Steps
    Given User is logged in to Automation practice app My Account page

  Scenario: Update Personal Information (First Name) in My Account
    When User clicks my personal information link
    And User updates first name correctly
    And User clicks Save button
    Then Success message is displayed

  Scenario: Order T-Shirt and Verify in order history
    When User clicks add to cart button after selecting TShirt to buy
    And User clicks through all Proceed to Checkout buttons
    And User clicks Confirm Order button after selecting payment method
    Then Order is displayed in Order history after click back to Order button


Feature: Validating Mock demo tool

  Scenario: Verify the use case 1, Existing user flow.

    Given Open a browser and navigate to the given URL
    When Delete all the screenshot Folders
    When Click on start demo  of use "useCase1StartDemo".
    Then it navigates to the cart details page capture screenshot and click on next Button.
    And navigates to the shipping details page enter the details using "TestInfo" and select "inputData" and click on next button
    And navigates to the OTP component page enter the details using "fillCode" and select "inputData" and click on next button
    And navigates to review page and click on next.
    And navigates to DCF page and click on next.
    And navigates to Order Confirmation page Click on "endDemo"

  Scenario: Verify the use case 2, New user flow.
    Given Open a browser and navigate to the given URL
    When Click on start demo  of use "useCase2StartDemo".
    Then it navigates to the cart details page and click on next Button.
    And Navigates to shipping details page and click on next Button.
    And Navigates to Add a card page and click on "TestInfo" and select "inputData" and click on next button.
    And Navigates to DCF page and  click on next button.
    And Navigates to payment process page and click on next Button
    And navigate to review page and click on next.
    And navigate to Order Confirmation page Click on "endDemo"







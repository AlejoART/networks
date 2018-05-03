Feature: As a google user, I want to be able to search for google map

Background:
Given I navigate to google page
  @googleSearch
  Scenario: should search for google map
    Given I enter "maps" in search text field
    And I click on google search button
    When I click on google maps tab from the list
    Then I view the google maps page and enter "webpt" in the google maps search field
    And I see the address of webpt
   
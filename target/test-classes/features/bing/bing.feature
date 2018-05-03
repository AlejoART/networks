Feature: As a bing user, I want to be able to search for bing map

Background:
Given I navigate to bing page
  @bingSearch
  Scenario: should search for bing map
    Given I enter "maps bing" in bing search text field
    And I click on bing search icon
    When I click on bing maps tab from the list
    Then I view the bing maps page and enter "webpt" in the bing maps search field
    And I see the address of webpt
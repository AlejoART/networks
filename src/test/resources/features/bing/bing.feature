@google
Feature: Bing Broad Search
  In order to find internet sites that have the information I'm looking for
  As an online consumer
  I need to be able to use Google to enter broad search terms and get meaningful site results

  Scenario: Search Bing for Amazon
    Given I am on the Bing search page
    When I enter the Bing search term 'amazon'
    And I click the Bing search icon
    Then I should see the 'www.amazon.com' in the bing search results


  Scenario: Search Bing for Ebay
    Given I am on the Bing search page
    When I enter the Bing search term 'ebay'
    And I click the Bing search icon
    Then I should see the 'www.ebay.com' in the bing search results
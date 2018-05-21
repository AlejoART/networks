package com.webpt.testing.starter.step.definition.google;

import com.webpt.testing.atf.page.PageComponentFactory;
import com.webpt.testing.starter.pages.GooglePageComponent;

import cucumber.api.java.en.*;

public class GoogleSteps {
	private GooglePageComponent googleSearch = (GooglePageComponent) 
			PageComponentFactory.getPageObject(new GooglePageComponent());
	
	@Given("^I am on the Google search page$")
	public void i_navigate_to_google_page () {
		googleSearch.get();
	}

	@When("^I enter the search term '(.*)'$")
	public void i_enter_text_in_search_text_field(String search) {
		googleSearch.googleSearchText(search);
	}

   @And("^I click the google search icon$")
   public void i_click_on_google_search_button() {
	   googleSearch.clickGoogleSearchBtn();
   }

   @When("^I click on google maps tab from the list$")
   public void i_click_on_google_maps_tab_from_the_list() {
	   googleSearch.clickGoogleMapTab();
   }

   @Then("^I view the google maps page and enter \"([^\"]*)\" in the google maps search field$")
   public void i_view_the_google_maps(String webpt) {
	   googleSearch.searchGoogleMapsField(webpt);
   }

   @And("^I see the address of webpt$")
   public void i_see_the_address_of_webpt() {
	   System.out.println("webpt address displays");
   }

	@Then("^I should see the '(.*)' in the google search results$")
	public void i_should_see_results(String term){
		googleSearch.resultsContain(term);
	}
}

package com.webpt.testing.atf.step.definition.google;

import com.webpt.testing.atf.page.PageComponentFactory;
import com.webpt.testing.atf.pageobject.google.GooglePageComponent;

import cucumber.api.java.en.*;

public class GoogleSteps {
	private GooglePageComponent googleSearch = (GooglePageComponent) 
			PageComponentFactory.getPageObject(new GooglePageComponent());
	
	@Given("^I navigate to google page$")
	public void i_navigate_to_google_page () {
		googleSearch.get();
	}
	@When("^I enter \"([^\"]*)\" in search text field$")
	public void i_enter_text_in_search_text_field(String search) {
		googleSearch.googleSearchText(search);
	}
   @And("^I click on google search button$")
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
}

package com.webpt.step.definition.google;

import cucumber.api.java.en.*;
import customapplications.PageComponentFactory;
import customapplications.pageobject.bing.BingPageComponent;

public class BingSteps {
	
	BingPageComponent bingSearch = (BingPageComponent) 
			PageComponentFactory.getPageObject(new BingPageComponent());
	
	@Given("^I navigate to bing page$")
	public void i_navigate_to_bing_page() {
		bingSearch.get();
	}
	@When("^I enter \"([^\"]*)\" in bing search text field$")
	public void i_enter_text_in_bing_search_text_field(String maps) {
		bingSearch.enterSearchText(maps);
	}
    @And("^I click on bing search icon$")
    public void i_click_on_bing_search_icon() {
    	bingSearch.clickSearchIcon();
    }
    @And("^I click on bing maps tab from the list$")
    public void i_click_on_bing_maps_tab_from_the_list() {
    	bingSearch.clickMapsTab();
    }
    @Then("^I view the bing maps page and enter \"([^\"]*)\" in the bing maps search field$")
    public void i_view_the_bing_maps_page(String name) {
    	bingSearch.enterMapsSearchText(name);
    }
}

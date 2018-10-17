package com.webpt.testing.starter.step.definition.bing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.atf.page.PageComponentFactory;
import com.webpt.testing.starter.pages.BingPageComponent;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.*;

public class BingSteps {
	
	BingPageComponent bingSearch = (BingPageComponent) 
			PageComponentFactory.getPageObject(new BingPageComponent());
	
	@Given("^I am on the Bing search page$")
	public void i_navigate_to_bing_page() {
		bingSearch.get();
	}

	@When("^I enter the Bing search term '(.*)'$")
	public void i_enter_text_in_bing_search_text_field(String term) {
		bingSearch.enterSearchText(term);
	}

    @And("^I click the Bing search icon$")
    public void i_click_on_bing_search_icon() {
    	bingSearch.clickSearchIcon();
    }

    @Then("^I should see the '(.*)' in the bing search results$")
	public void i_should_see_results(String term){
		bingSearch.resultsContain(term);
	}

    @And("^I click on bing maps tab from the list$")
    public void i_click_on_bing_maps_tab_from_the_list() {
		bingSearch.clickMapsTab();
    }
    @After
    public void endTestCase(Scenario result) throws IOException {

		if(result.isFailed()) {
			File screenshot = ((TakesScreenshot) WebptATFHandler.getInstance().getWebAutomation().getWebDriver()).getScreenshotAs(OutputType.FILE);
			InputStream screenshotStream = new FileInputStream(screenshot);

			result.embed(IOUtils.toByteArray(screenshotStream), "image/jpeg");
		}
	}

}

package com.webpt.testing.starter.pages;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.atf.config2.WebptConfigurationManager;



public class GooglePageComponent extends LoadableComponent<GooglePageComponent> {
	
	private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
	private final String GOOGLESEARCHFIELD_ID = "lst-ib";
	private final String GOOGLESEARCHBTN_CSS = ".jsb input[name='btnK']";
	private final String GOOGLEMAPSTAB_CSS = "#rso a:nth-of-type(" + 1 + ")";
	private final String SEARCHGOOGLEMAPS_ID = "searchboxinput";

	@FindBy(id = GOOGLESEARCHFIELD_ID)
	private WebElement google_search_text;
	@FindBy(css = GOOGLESEARCHBTN_CSS)
	private WebElement google_search_btn;
	@FindBy(css = GOOGLEMAPSTAB_CSS)
	private WebElement googleMapsTab;
	@FindBy(id = SEARCHGOOGLEMAPS_ID)
	private WebElement searchGoogleMaps;
	@FindBy(id ="rcnt")
	private WebElement searchResults;

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.get(WebptConfigurationManager.getInstance().getGoogleUrl());
	}

	@Override
	protected void isLoaded() throws Error {
		// TODO Auto-generated method stub
		String url = "";
		try {
			url = driver.getCurrentUrl();
			System.out.println(url);
		} catch (NoSuchElementException nsee) {
			// Do nothing assertion will fail and the load() will be called
		}

		assertTrue("there is no issue with the entry page:  " + url, url.endsWith("/"));

	}
	
	public void googleSearchText(String search) {
		try {
			google_search_text.click();
			google_search_text.clear();
			google_search_text.sendKeys(search);
			google_search_text.sendKeys(Keys.TAB);
		}catch(Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
	public void clickGoogleSearchBtn() {
		try {
			google_search_btn.click();
		}catch(Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
	public void clickGoogleMapTab() {
		try {
			googleMapsTab.click();
			Thread.sleep(2000);
		}catch(Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
	public void searchGoogleMapsField(String webpt) {
		try {
			Thread.sleep(2000);
			searchGoogleMaps.click();
			searchGoogleMaps.clear();
			searchGoogleMaps.sendKeys(webpt);
			searchGoogleMaps.sendKeys(Keys.TAB);
		}catch(Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
	public void resultsContain(String term){
		Assert.assertTrue(searchResults.getText().contains(term));
	}

}

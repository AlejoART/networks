package com.webpt.testing.atf.pageobject.bing;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.atf.managers.WebptConfigurationManager;

public class BingPageComponent extends LoadableComponent<BingPageComponent> {
	private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
	private final String BINGSEARCHFIELD_CLASS = "b_searchbox";
	private final String BINGSEARCHBTN_CLASS = "b_searchboxSubmit";
	private final String BINGMAPSTAB_CSS = "#b_results li:nth-of-type(" + 1 + ") a";
	private final String MAPSSEARCH_ID = "maps_sb";

	protected void load() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.get(WebptConfigurationManager.getInstance().getBingUrl());
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

	@FindBy(className = BINGSEARCHFIELD_CLASS)
	private WebElement bingSearchField;
	@FindBy(className = BINGSEARCHBTN_CLASS)
	private WebElement clickSearchBtn;
	@FindBy(css = BINGMAPSTAB_CSS)
	private WebElement bingMapsTab;
	@FindBy(id = MAPSSEARCH_ID)
	private WebElement mapsSearchField;

	public void enterSearchText(String maps) {
		try {
			bingSearchField.click();
			bingSearchField.clear();
			bingSearchField.sendKeys(maps);
			bingSearchField.sendKeys(Keys.TAB);
		} catch (Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
	public void clickSearchIcon() {
		try {
			clickSearchBtn.click();
			
		}catch (Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
	public void clickMapsTab() {
		try {
			
			bingMapsTab.click();
			
		}catch (Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
	public void enterMapsSearchText(String name) {
		try {
	
			mapsSearchField.click();
			mapsSearchField.clear();
			mapsSearchField.sendKeys(name);
			mapsSearchField.sendKeys(Keys.TAB);
		} catch (Exception e) {
			Assert.fail("failed due to:  " + e.getMessage());
		}
	}
}
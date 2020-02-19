package com.webpt.testing.starter.pages;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.starter.config.Config;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertTrue;

public class EmrDashboardComponent extends LoadableComponent<EmrDashboardComponent> {

    private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
    private Config config;

    @FindBy(css = ".base-default-lightbox-outer")
    private WebElement popUp;

    @FindBy(css = ".base-default-lightbox-outer .base-default-tool-close-image")
    private WebElement closePop;

    @FindBy(css = ".x-panel-header")
    private WebElement panelHeader;

    @FindBy(linkText = "View Schedule")
    private WebElement viewSchedLink;

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
    }

    public void isDashboardDisplayed() {
        WebDriverWait ewait = new WebDriverWait(driver, 15);
        ewait.until(ExpectedConditions.visibilityOf(popUp));
        closePop.click();
        Assert.assertTrue(panelHeader.isDisplayed());
    }

    public void goToScheduler(){
        try {
            viewSchedLink.click();
            Thread.sleep(2000);
        }catch(Exception e) {
            Assert.fail("failed due to:  " + e.getMessage());
        }
    }
}

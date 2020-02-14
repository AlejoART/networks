package com.webpt.testing.starter.pages;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.starter.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.testng.Assert;

public class EmrSchedulerPageComponent extends LoadableComponent<EmrSchedulerPageComponent> {
    private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
    private Config config;

    @FindBy(partialLinkText = "Manage Calendars")
    private WebElement manageCalLink;

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }

    public void goToSchedSettings(){
        try {
            manageCalLink.click();
            Thread.sleep(2000);
        }catch(Exception e) {
            Assert.fail("failed due to:  " + e.getMessage());
        }
    }
}

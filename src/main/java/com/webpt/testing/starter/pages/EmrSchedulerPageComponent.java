package com.webpt.testing.starter.pages;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.starter.config.Config;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class EmrSchedulerPageComponent extends LoadableComponent<EmrSchedulerPageComponent> {
    private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
    private Config config;

    @FindBy(partialLinkText = "Manage Calendars")
    private WebElement manageCalLink;
    @FindBy(id = "legend")
    private WebElement legendBtn;
    @FindBy(id = "legend-window")
    private WebElement legendWindow;
    @FindBy(id = "customApp__7")
    private WebElement patientSSappType;

    private WebDriverWait wait = new WebDriverWait(driver, 100);

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

    public void openLegend(){
        try {
            wait.until(ExpectedConditions.elementToBeClickable(legendBtn));
            legendBtn.click();
            wait.until(ExpectedConditions.visibilityOf(legendWindow));
        }catch (Exception e){
            Assert.fail("failed due to:  " + e.getMessage());
        }
    }

    public Boolean isPatientSSdisplayed(){
        return patientSSappType.isDisplayed();
    }
}

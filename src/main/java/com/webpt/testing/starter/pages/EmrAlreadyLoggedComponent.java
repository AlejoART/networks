package com.webpt.testing.starter.pages;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.starter.config.Config;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertTrue;

public class EmrAlreadyLoggedComponent extends LoadableComponent<EmrAlreadyLoggedComponent> {

    private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
    private Config config;
    private WebDriverWait wait = new WebDriverWait(driver, 15);

    @FindBy(className="cancel")
    private WebElement logoutButton;
    @FindBy(className="ok")
    private WebElement oustButton;

    /*public String getPageUrl() {
        return config.getEmrAppUrl() + "/oust";
    }
    public boolean isCurrentPage() {
        List<WebElement> elements = driver.findElements(By.className("eviction-option"));
        return elements.size() > 0;
    }
    public void oust() {
        wait.until(ExpectedConditions.elementToBeClickable(oustButton));
        oustButton.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.className("eviction-option"), 0));
    }
    public void clickIgnoreBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(ignoreBtn));
        ignoreBtn.click();
    }*/

    public EmrAlreadyLoggedComponent() {
        this.config = new Config();
    }

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
    }

    public void closeOpenedSession() {
        if (oustButton.isDisplayed()){
            oustButton.click();
//            try {
//                oustButton.click();
//            } catch (TimeoutException e) {
//                System.out.println("IGNORING TIMEOUT");
//            }
        }
//        else {
//            try { }
//            catch (TimeoutException e) {
//                System.out.println("IGNORING TIMEOUT");
//            }
//        }
    }


}

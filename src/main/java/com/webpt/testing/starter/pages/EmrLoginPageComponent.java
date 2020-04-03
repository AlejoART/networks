package com.webpt.testing.starter.pages;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.atf.page.PageComponentFactory;
import com.webpt.testing.starter.config.Config;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static org.testng.AssertJUnit.assertTrue;

public class EmrLoginPageComponent extends LoadableComponent<EmrLoginPageComponent> {

    private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
    private Config config;

    @FindBy(id = "login-username")
    private WebElement loginUsername;
    @FindBy(id = "login-password")
    private WebElement loginPassword;
    @FindBy(id = "login-button")
    private WebElement loginButton;

    private WebDriverWait wait = new WebDriverWait(driver, 15);

    public EmrLoginPageComponent() {
        this.config = new Config();
    }

    @Override
    protected void load() {
        // TODO Auto-generated method stub
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        driver.get(config.getEmrLoginUrl());
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

    public void enterUserName(String userName){
        loginUsername.sendKeys(userName);
    }

    public void enterPassword(String password){
        loginPassword.sendKeys(password);
    }

    public void clickLoginBtn() {
        try {
            loginButton.click();
            EmrDashboardComponent emrDashboardComponent = (EmrDashboardComponent) PageComponentFactory.getPageObject(new EmrDashboardComponent());
            EmrAlreadyLoggedComponent emrAlreadyLoggedComponent = (EmrAlreadyLoggedComponent) PageComponentFactory.getPageObject(new EmrAlreadyLoggedComponent());
            try {
                if (emrDashboardComponent.getPopUp().isDisplayed()){
                    try {
                        emrDashboardComponent.getClosePop().click();
                    } catch (TimeoutException e) {
                        System.out.println("IGNORING TIMEOUT");
                    }
                }
            } catch (Exception e1){
                if(emrAlreadyLoggedComponent.getOustButton().isDisplayed()){
                    try {
                        emrAlreadyLoggedComponent.closeOpenedSession();
                        wait.until(ExpectedConditions.visibilityOf(emrDashboardComponent.getPopUp()));
                        emrDashboardComponent.getClosePop().click();
                    } catch (TimeoutException e2) {
                        System.out.println("IGNORING TIMEOUT");
                    }
                }
            }

        } catch (Exception e) {
                Assert.fail("failed due to: " + e.getMessage());
            }
        }
    }



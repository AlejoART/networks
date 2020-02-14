package com.webpt.testing.starter.pages;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.starter.config.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.swing.*;
import java.util.List;

public class EmrSchedulerSettingsPageComponent extends LoadableComponent<EmrSchedulerSettingsPageComponent> {
    private WebDriver driver = WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
    private Config config;

    @FindBy(id = "SCHED_innertabcontainer__Scheduling")
    private WebElement selfSchedTab;

    @FindBy(id = "Scheduling-Calendar-Combobox")
    private WebElement calSelectField;

    @FindBy(css = ".x-combo-list-inner")
    private WebElement comboList;

    @FindBy(css = "#Scheduling-Calendar-Combobox + .x-form-trigger.x-form-arrow-trigger")
    private WebElement listDropd;

    @FindBy(css = ".x-combo-list-ft .x-toolbar-left-row .x-btn-text.x-tbar-page-next")
    private WebElement calDropdNextBtn;

    @FindBy(css = ".x-combo-list-ft tr :nth-of-type(6) .xtb-text")
    private WebElement calPagesTotal;

    @FindBy(id = "Scheduling-Checkbox-Value")
    private WebElement schedCheckbox;

    @FindBy(id = "Scheduling-Location-Value")
    private WebElement schedLocation;

    @FindBy(id = "Scheduling-Therapist-Value")
    private WebElement schedTherapist;

    @FindBy(id = "Scheduling-Settings-Value")
    private WebElement schedSettings;

    @FindBy(id = "monday-sod")
    private WebElement mondaySOD;
    @FindBy(id = "monday-eod")
    private WebElement mondayEOD;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-monday")
    private WebElement mondayAvailability;

    @FindBy(id = "tuesday-sod")
    private WebElement tuesdaySOD;
    @FindBy(id = "tuesday-eod")
    private WebElement tuesdayEOD;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-tuesday")
    private WebElement tuesdayAvailability;

    @FindBy(id = "wednesday-sod")
    private WebElement wednesdaySOD;
    @FindBy(id = "wednesday-eod")
    private WebElement wednesdayEOD;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-wednesday")
    private WebElement wednesdayAvailability;

    @FindBy(id = "thursday-sod")
    private WebElement thursdaySOD;
    @FindBy(id = "thursday-eod")
    private WebElement thursdayEOD;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-thursday")
    private WebElement thursdayAvailability;

    @FindBy(id = "friday-sod")
    private WebElement fridaySOD;
    @FindBy(id = "friday-eod")
    private WebElement fridayEOD;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-friday")
    private WebElement fridayAvailability;

    @FindBy(id = "saturday-sod")
    private WebElement saturdaySOD;
    @FindBy(id = "saturday-eod")
    private WebElement saturdayEOD;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-saturday")
    private WebElement saturdayAvailability;

    @FindBy(id = "sunday-sod")
    private WebElement sundaySOD;
    @FindBy(id = "sunday-eod")
    private WebElement sundayEOD;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-sunday")
    private WebElement sundayAvailability;

    @FindBy(css = "#Scheduling-Settings-Value + .x-form-arrow-trigger")
    private WebElement schedSettingsDropdown;

    private WebDriverWait wait = new WebDriverWait(driver, 15);

    @Override
    protected void load() {
    }

    @Override
    protected void isLoaded() throws Error {
    }

    public void goToSelfSchedTab(){
        try {
            selfSchedTab.click();
            //Thread.sleep(2000);
        }catch(Exception e) {
            Assert.fail("failed due to:  " + e.getMessage());
        }
    }

    public void calSelectFieldIsDisplayed(){
        Assert.assertTrue(calSelectField.isDisplayed());
    }

    public void selectOneCalendar(String calName) throws InterruptedException {
        listDropd.click();
        Thread.sleep(2000);
//        comboList = driver.findElement(By.id("ext-gen315"));
//        wait.until(ExpectedConditions.visibilityOf(comboList));
        List<WebElement> currentCalendars;
        int totalCalPages = Integer.parseInt(calPagesTotal.getText().replace("of ",""));
        calendarPages:
        for (int i = 0; i < totalCalPages; i++) {
            currentCalendars = comboList.findElements(By.className("x-combo-list-item"));
            for (int j = 0; j < currentCalendars.size(); j++) {
                if (currentCalendars.get(j).getText().equals(calName)) {
                    currentCalendars.get(j).click();
                    Thread.sleep(1000);
                    break calendarPages;
                }
            }
            //javascript.executeScript("document.getElementById('ext-gen350').click();");
            calDropdNextBtn.click();
            //wait.until(ExpectedConditions.visibilityOf(comboList));
            Thread.sleep(2000);
        }
    }

    public Boolean allFieldsAreDisplayed(){
       return (schedCheckbox.isDisplayed() && schedLocation.isDisplayed() && schedTherapist.isDisplayed() && schedSettings.isDisplayed());
    }

    public void clickOnCustomize(){
        if (schedSettings.getText().equals("Use location settings")){
            schedSettingsDropdown.click();
        }
    }

    public Boolean defaultTimesAreOk(){
        String sod = "7am";
        String eod = "6pm";
        return (mondaySOD.getText().equals(sod) && mondayEOD.getText().equals(eod) && !mondayAvailability.isSelected()
                && tuesdaySOD.getText().equals(sod) && tuesdayEOD.getText().equals(eod) && !tuesdayAvailability.isSelected()
                    && wednesdaySOD.getText().equals(sod) && wednesdayEOD.getText().equals(eod) && !wednesdayAvailability.isSelected()
                        && thursdaySOD.getText().equals(sod) && thursdayEOD.getText().equals(eod) && !thursdayAvailability.isSelected()
                            && fridayAvailability.getText().equals(sod) && fridayAvailability.getText().equals(eod) && !fridayAvailability.isSelected()
                                && saturdaySOD.getText().equals(sod) && saturdayEOD.getText().equals(eod) && saturdayAvailability.isSelected()
                                    && sundaySOD.getText().equals(sod) && sundayEOD.getText().equals(eod) && sundayAvailability.isSelected());
    }
}

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
    @FindBy(css = "#monday-sod + .x-form-arrow-trigger")
    private WebElement mondaySODdrop;
    @FindBy(id = "monday-eod")
    private WebElement mondayEOD;
    @FindBy(css = "#monday-eod + .x-form-arrow-trigger")
    private WebElement mondayEODdrop;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-monday")
    private WebElement mondayAvailability;

    @FindBy(id = "tuesday-sod")
    private WebElement tuesdaySOD;
    @FindBy(css = "#tuesday-sod + .x-form-arrow-trigger")
    private WebElement tuesdaySODdrop;
    @FindBy(id = "tuesday-eod")
    private WebElement tuesdayEOD;
    @FindBy(css = "#tuesday-eod + .x-form-arrow-trigger")
    private WebElement tuesdayEODdrop;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-tuesday")
    private WebElement tuesdayAvailability;

    @FindBy(id = "wednesday-sod")
    private WebElement wednesdaySOD;
    @FindBy(css = "#wednesday-sod + .x-form-arrow-trigger")
    private WebElement wednesdaySODdrop;
    @FindBy(id = "wednesday-eod")
    private WebElement wednesdayEOD;
    @FindBy(css = "#wednesday-eod + .x-form-arrow-trigger")
    private WebElement wednesdayEODdrop;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-wednesday")
    private WebElement wednesdayAvailability;

    @FindBy(id = "thursday-sod")
    private WebElement thursdaySOD;
    @FindBy(css = "#thursday-sod + .x-form-arrow-trigger")
    private WebElement thursdaySODdrop;
    @FindBy(id = "thursday-eod")
    private WebElement thursdayEOD;
    @FindBy(css = "#thursday-eod + .x-form-arrow-trigger")
    private WebElement thursdayEODdrop;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-thursday")
    private WebElement thursdayAvailability;

    @FindBy(id = "friday-sod")
    private WebElement fridaySOD;
    @FindBy(css = "#friday-sod + .x-form-arrow-trigger")
    private WebElement fridaySODdrop;
    @FindBy(id = "friday-eod")
    private WebElement fridayEOD;
    @FindBy(css = "#friday-eod + .x-form-arrow-trigger")
    private WebElement fridayEODdrop;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-friday")
    private WebElement fridayAvailability;

    @FindBy(id = "saturday-sod")
    private WebElement saturdaySOD;
    @FindBy(css = "#saturday-sod + .x-form-arrow-trigger")
    private WebElement saturdaySODdrop;
    @FindBy(id = "saturday-eod")
    private WebElement saturdayEOD;
    @FindBy(css = "#saturday-eod + .x-form-arrow-trigger")
    private WebElement saturdayEODdrop;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-saturday")
    private WebElement saturdayAvailability;

    @FindBy(id = "sunday-sod")
    private WebElement sundaySOD;
    @FindBy(css = "#sunday-sod + .x-form-arrow-trigger")
    private WebElement sundaySODdrop;
    @FindBy(id = "sunday-eod")
    private WebElement sundayEOD;
    @FindBy(css = "#sunday-eod + .x-form-arrow-trigger")
    private WebElement sundayEODdrop;
    @FindBy(id = "Self-Scheduling-Week-Checkbox-sunday")
    private WebElement sundayAvailability;

    @FindBy(css = "#Scheduling-Settings-Value + .x-form-arrow-trigger")
    private WebElement schedSettingsDropdown;
    @FindBy(css = ".x-layer .x-combo-selected")
    private List<WebElement> schedSettingsSelected;
    @FindBy(css = ".x-layer .x-combo-selected + .x-combo-list-item")
    private List<WebElement> schedSettingsToSelect;

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
            Thread.sleep(3000);
        }
    }

    public Boolean allFieldsAreDisplayed(){
       return (schedCheckbox.isDisplayed() && schedLocation.isDisplayed() && schedTherapist.isDisplayed() && schedSettings.isDisplayed());
    }

    public void clickOnCustomize(){
        schedSettingsDropdown.click();
        String selectedOption = schedSettingsSelected.get(1).getText();
        if (selectedOption.equals("Use location settings")){
            schedSettingsToSelect.get(0).click();
        }
    }

    public Boolean defaultTimesAreOk(){
        String sod = "7am";
        String eod = "6pm";
        boolean monSODFlag, monEODFlag, monFlag, tueSODFlag, tueEODFlag, tueFlag, wedSODFlag, wedEODFlag, wedFlag, thuSODFlag, thuEODFlag, thuFlag, friSODFlag, friEODFlag, friFlag, satSODFlag, satEODFlag, satFlag, sunSODFlag, sunEODFlag, sunFlag;

        mondaySODdrop.click();
        monSODFlag = schedSettingsSelected.get(2).getText().equals(sod);
        mondayEODdrop.click();
        monEODFlag = schedSettingsSelected.get(3).getText().equals(eod);
        monFlag = (monSODFlag && monEODFlag);

        tuesdaySODdrop.click();
        tueSODFlag = schedSettingsSelected.get(4).getText().equals(sod);
        tuesdayEODdrop.click();
        tueEODFlag = schedSettingsSelected.get(5).getText().equals(eod);
        tueFlag = (tueSODFlag && tueEODFlag);

        wednesdaySODdrop.click();
        wedSODFlag = schedSettingsSelected.get(6).getText().equals(sod);
        wednesdayEODdrop.click();
        wedEODFlag = schedSettingsSelected.get(7).getText().equals(eod);
        wedFlag = (wedSODFlag && wedEODFlag);

        thursdaySODdrop.click();
        thuSODFlag = schedSettingsSelected.get(8).getText().equals(sod);
        thursdayEODdrop.click();
        thuEODFlag = schedSettingsSelected.get(9).getText().equals(eod);
        thuFlag = (thuSODFlag && thuEODFlag);

        fridaySODdrop.click();
        friSODFlag = schedSettingsSelected.get(10).getText().equals(sod);
        fridayEODdrop.click();
        friEODFlag = schedSettingsSelected.get(11).getText().equals(eod);
        friFlag = (friSODFlag && friEODFlag);


        try {
            saturdayAvailability.click();
        }catch (Exception e){
            Assert.fail("failed due to:  " + e.getMessage());
        }
        saturdaySODdrop.click();
        satSODFlag = schedSettingsSelected.get(12).getText().equals(sod);
        saturdayEODdrop.click();
        satEODFlag = schedSettingsSelected.get(13).getText().equals(eod);
        satFlag = (satSODFlag && satEODFlag);

        try {
            sundayAvailability.click();
        }catch (Exception e){
            Assert.fail("failed due to:  " + e.getMessage());
        }
        sundaySODdrop.click();
        sunSODFlag = schedSettingsSelected.get(14).getText().equals(sod);
        sundayEODdrop.click();
        sunEODFlag = schedSettingsSelected.get(15).getText().equals(eod);
        sunFlag = (sunSODFlag && sunEODFlag);

        return monFlag && tueFlag && wedFlag && thuFlag && friFlag && satFlag && sunFlag;
    }
}

package com.webpt.testing.starter.step.definition.emr.scheduling;

import com.webpt.testing.atf.page.PageComponentFactory;
import com.webpt.testing.starter.pages.EmrDashboardComponent;
import com.webpt.testing.starter.pages.EmrSchedulerPageComponent;
import com.webpt.testing.starter.pages.EmrSchedulerSettingsPageComponent;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class EmrSelfSchedTabSteps {
    private EmrDashboardComponent emrDashboardComponent = (EmrDashboardComponent) PageComponentFactory.getPageObject(new EmrDashboardComponent());
    private EmrSchedulerPageComponent emrSchedulerPageComponent = (EmrSchedulerPageComponent) PageComponentFactory.getPageObject(new EmrSchedulerPageComponent());
    private EmrSchedulerSettingsPageComponent emrSchedulerSettingsPageComponent = (EmrSchedulerSettingsPageComponent) PageComponentFactory.getPageObject(new EmrSchedulerSettingsPageComponent());

    @And("I click on view schedule link")
    public void iClickOnViewScheduleLink() {
        emrDashboardComponent.goToScheduler();
    }

    @And("I click on manage calendars link")
    public void iClickOnManageCalendarsLink() {
        emrSchedulerPageComponent.goToSchedSettings();
    }

    @And("I click on self scheduling tab")
    public void iClickOnSelfSchedulingTab() {
        emrSchedulerSettingsPageComponent.goToSelfSchedTab();
    }

    @Then("I view the calendar selection field only")
    public void iViewTheCalendarSelectionFieldOnly() {
        emrSchedulerSettingsPageComponent.calSelectFieldIsDisplayed();
    }

    @And("I select a calendar")
    public void iSelectACalendar() throws InterruptedException {
        emrSchedulerSettingsPageComponent.selectOneCalendar("Alberto Einsteino  ");
    }

    @Then("I view the the other hidden fields")
    public void iViewTheTheOtherHiddenFields() {
        Assert.assertTrue(emrSchedulerSettingsPageComponent.allFieldsAreDisplayed());
    }

    @And("I select Customize in the Scheduling Availability section")
    public void iSelectCustomizeInTheSchedulingAvailabilitySection() {
        emrSchedulerSettingsPageComponent.clickOnCustomize();
    }

    @Then("I view the default availability times for the week")
    public void iViewTheDefaultAvailabilityTimesForTheWeek() {
        Assert.assertTrue(emrSchedulerSettingsPageComponent.defaultTimesAreOk());
    }

    @And("I type a calendar name '(.*)'")
    public void iTypeACalendarName(String calName) throws InterruptedException {
        emrSchedulerSettingsPageComponent.typeCalendarName(calName, emrSchedulerSettingsPageComponent.getCalSelectField());
    }

    @And("I select another therapist")
    public void iSelectAnotherTherapist() throws InterruptedException {
        emrSchedulerSettingsPageComponent.selectAnotherTherapist("Alan Mazur");
    }

    @And("I click on save button")
    public void iClickOnSaveButton() {
        emrSchedulerSettingsPageComponent.save();
    }

    @Then("I view the warning message")
    public void iViewTheWarningMessage() {
        Assert.assertTrue(emrSchedulerSettingsPageComponent.correctWarningMessage());
    }

    @And("I click on Calendar Settings and User permission tab")
    public void iClickOnCalendarSettingsAndUserPermissionTab() {
        emrSchedulerSettingsPageComponent.goToCalSettingsTab();
    }

    @And("I type a calendar name in calendar settings tab '(.*)'")
    public void iTypeACalendarNameInCalendarSettingsTab(String calName) throws InterruptedException {
        emrSchedulerSettingsPageComponent.typeCalendarName(calName, emrSchedulerSettingsPageComponent.getCalSelectionCalendarTab());
    }

    @And("I view that the calendar is active")
    public void iViewThatTheCalendarIsActive() {
        Assert.assertTrue(emrSchedulerSettingsPageComponent.calIsActive());
    }

    @Then("I view that the calendar is displayed")
    public void iViewThatTheCalendarIsDisplayed() {
        Assert.assertTrue(emrSchedulerSettingsPageComponent.calIsDisplayed());
    }

}

package com.webpt.testing.starter.step.definition.emr.scheduling;

import com.webpt.testing.atf.page.PageComponentFactory;
import com.webpt.testing.starter.pages.EmrSchedulerPageComponent;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import org.testng.Assert;

public class EmrPatientSSnewApptSteps {
    private EmrSchedulerPageComponent emrSchedulerPageComponent = (EmrSchedulerPageComponent) PageComponentFactory.getPageObject(new EmrSchedulerPageComponent());

    @And("I click on Legend")
    public void iClickOnLegend() {
        emrSchedulerPageComponent.openLegend();
    }

    @Then("I view the Patient Self Schedule type")
    public void iViewThePatientSelfScheduleType() {
        Assert.assertTrue(emrSchedulerPageComponent.isPatientSSdisplayed());
    }
}

package com.webpt.testing.starter.step.definition.emr.scheduling;

import com.webpt.testing.atf.page.PageComponentFactory;
import com.webpt.testing.starter.pages.EmrAlreadyLoggedComponent;
import com.webpt.testing.starter.pages.EmrDashboardComponent;
import com.webpt.testing.starter.pages.EmrLoginPageComponent;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.NoSuchElementException;

public class EmrLoginSteps {
    private EmrLoginPageComponent emrLoginPageComponent = (EmrLoginPageComponent) PageComponentFactory.getPageObject(new EmrLoginPageComponent());
    private EmrDashboardComponent emrDashboardComponent = (EmrDashboardComponent) PageComponentFactory.getPageObject(new EmrDashboardComponent());

    @Given("I navigate to emr login page")
    public void i_navigate_to_emr_login_page() {
        // Write code here that turns the phrase above into concrete actions
        emrLoginPageComponent.get();
    }

    @When("I enter username '(.*)'")
    public void i_enter_username(String user) {
        // Write code here that turns the phrase above into concrete actions
        emrLoginPageComponent.enterUserName(user);
    }

    @When("I enter password '(.*)'")
    public void iEnterPasswordAbc(String pass) {
        emrLoginPageComponent.enterPassword(pass);
    }

    @When("I click on login button")
    public void i_click_on_login_button() {
        // Write code here that turns the phrase above into concrete actions
        emrLoginPageComponent.clickLoginBtn();
    }

    @Then("I view the emr dashboard page")
    public void i_view_the_emr_dashboard_page() {
        // Write code here that turns the phrase above into concrete actions
        emrDashboardComponent.isDashboardDisplayed();
    }


}

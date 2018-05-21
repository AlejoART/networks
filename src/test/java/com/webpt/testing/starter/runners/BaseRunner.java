package com.webpt.testing.starter.runners;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.atf.web.ScreenShot;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.ITestResult;
import org.testng.annotations.*;

@CucumberOptions(
        glue={"com.webpt.testing.starter.step.definition"})
public class BaseRunner {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void scenario(PickleEventWrapper pickleEvent, CucumberFeatureWrapper cucumberFeature) throws Throwable {
        testNGCucumberRunner.runScenario(pickleEvent.getPickleEvent());
    }

    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod
    public void screenShot(ITestResult result){
        if(ITestResult.FAILURE==result.getStatus()){
            ScreenShot screenShot = new ScreenShot();
            screenShot.capture(result.getInstanceName() + "_" + ITestResult.FAILURE);
        }
        WebptATFHandler.getInstance().teardown();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() throws Exception {
        testNGCucumberRunner.finish();
    }

}

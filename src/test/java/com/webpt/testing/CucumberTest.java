package com.webpt.testing;

import com.webpt.testing.atf.WebptATFHandler;
import com.webpt.testing.atf.web.ScreenShot;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.ITestResult;
import org.testng.annotations.*;

@CucumberOptions(
        features={"src/test/resources/features"},
        plugin = {"json:target/cucumber/data/cucumber.json" },
        glue={"com.webpt.testing.step.definition"})
public class CucumberTest {

    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setUpClass() throws Exception {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(dataProvider = "features")
    public void feature(CucumberFeatureWrapper cucumberFeature) {
        testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }

    @DataProvider(parallel = true)
    public Object[][] features() {
        return testNGCucumberRunner.provideFeatures();
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

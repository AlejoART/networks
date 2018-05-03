package webptcustomapplication.analytics;
import org.testng.annotations.*;
import org.testng.ITestResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.webpt.WebptATFHandler;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.AbstractTestNGCucumberTests;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.*;

@CucumberOptions(
    strict = true,
    features = {"/Users/sayed.maudoudi/Desktop/webpt-cucumber-starter/src/test/resources/features/google/google.feature"},
      plugin = {"json:target/output/json/2.json", "pretty" },
      format = {"json:target/output/json/2.json", "pretty:STDOUT", "html:target/site/cucumber-pretty"}, 
     //tags = { "~@ignore"  }, 
     monochrome = true, 
    glue = { "com.webpt.step.definition.google" } )

    
public class Parallel02IT {
	
	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = { "smokeTest" }, description = "Runs Cucumber Feature", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}
	@BeforeMethod
	public void deleteFiles() {
	String path = new File("").getAbsolutePath();
        String absPath = path + "/screenshot/";
        File dir = new File(absPath);
        if (dir.exists()) {
	        for (File f : dir.listFiles()) {
			           f.delete();
		    }
		  }
		  else
		    System.out.println("creating a folder" );
	 }   
	@AfterMethod 
     public void screenShot(ITestResult result){
        String path = new File("").getAbsolutePath();
        String absPath = path + "/screenshot/";
        Random random = new Random();
        int num = random.nextInt(100) + 1;
 		if(ITestResult.FAILURE==result.getStatus()){
 			try{
 				
 				TakesScreenshot screenshot=(TakesScreenshot)WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
 				File src=screenshot.getScreenshotAs(OutputType.FILE);
 			    FileUtils.copyFile(src, new File(absPath + result.getInstanceName() + "_" + ITestResult.FAILURE + ".png"));
 			     System.out.println("Successfully captured failing step screenshots");
			 }catch (Exception e){
			    e.getStackTrace();
			 	
			 } 
 		}
 	}

	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception {
		testNGCucumberRunner.finish();
		WebptATFHandler.getInstance().teardown(); 
	} 
}
	
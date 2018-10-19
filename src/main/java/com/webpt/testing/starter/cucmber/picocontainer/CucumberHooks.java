package com.webpt.testing.starter.cucmber.picocontainer;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.webpt.testing.atf.WebptATFHandler;

public class CucumberHooks {
	@After
    public static void embedFailureScreenShotsInReports(Scenario result) throws IOException {
         if(result.isFailed()) {
			File screenshot = ((TakesScreenshot) WebptATFHandler.getInstance().getWebAutomation().getWebDriver()).getScreenshotAs(OutputType.FILE);
			InputStream screenshotStream = new FileInputStream(screenshot);
            result.embed(IOUtils.toByteArray(screenshotStream), "image/jpeg");
		}
	}
}

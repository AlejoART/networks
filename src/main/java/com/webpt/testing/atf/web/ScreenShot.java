package com.webpt.testing.atf.web;

import com.webpt.testing.atf.WebptATFHandler;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class ScreenShot {

    public String capture(String name){
        String path = new File("").getAbsolutePath();
        String imagePath = path + "/target/cucumber/screenshot/" + name + ".png";
        try {
            TakesScreenshot screenshot = (TakesScreenshot) WebptATFHandler.getInstance().getWebAutomation().getWebDriver();
            File src = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File(imagePath));
            System.out.println("Successfully captured failing step screenshot to: " + imagePath);
            return imagePath;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

}

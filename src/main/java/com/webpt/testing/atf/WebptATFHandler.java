package com.webpt.testing.atf;

import net.jsourcerer.webdriver.jserrorcollector.JavaScriptError;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.webpt.testing.atf.managers.*;
import com.webpt.testing.atf.managers.*;

import java.util.List;

public class WebptATFHandler
{

	private static Logger log = LoggerFactory.getLogger(WebptATFHandler.class);

	private static WebptATFHandler webptHandlerInstance;

	private WebptATFHandler()
	{
	}

	/**
	 * WebptATFHandler.getInstance
	 * 
	 * @return the instance of the WebptAFTHandler
	 */
	public static WebptATFHandler getInstance()
	{
		if (webptHandlerInstance == null)
		{
			synchronized (WebptATFHandler.class)
			{
				WebptATFHandler inst = webptHandlerInstance;
				if (inst == null)
				{
					synchronized (WebptATFHandler.class)
					{
						webptHandlerInstance = new WebptATFHandler();
						log.info("Created new instance of the ATFHandler.");
					}
				}
			}
		}
		return webptHandlerInstance;
	}

	private volatile WebptWebAutomationManager webAutomationInstance;

	/**
	 * getWebAutomation Used to perform web automation tasks
	 */
	public WebptWebAutomationManager getWebAutomation()
	{
		if (webAutomationInstance == null)
		{
			synchronized (WebptWebAutomationManager.class)
			{
				WebptWebAutomationManager inst = webAutomationInstance;
				if (inst == null)
				{
					synchronized (WebptWebAutomationManager.class)
					{
						webAutomationInstance = new WebptWebAutomationManager();
						log.info("Created new instance of the Web Automation Manager.");
					}
				}
			}
		}
		return webAutomationInstance;
	}

	/**
	 * teardown Will cleanup any resources used within the instance of this
	 * WebptATFHandler
	 */
	public void teardown()
	{
		if (webAutomationInstance != null)
		{
			if (webAutomationInstance != null)
			{
				// Log the js error collected if this reporting is on and we
				// were using a firefox driver
				if (webAutomationInstance.getWebDriver() instanceof FirefoxDriver && WebptConfigurationManager.getInstance().getWebUseJSErrorCollectorWithFirefox())
				{
					List<JavaScriptError> jsErrors = JavaScriptError.readErrors(webAutomationInstance.getWebDriver());
					for (JavaScriptError jsErr : jsErrors)
					{
						log.error("JSErrorCollected: Line: " + jsErr.getLineNumber() + " Source: " + jsErr.getSourceName() + " Error: " + jsErr.getErrorMessage());
					}
				}
			}

			webAutomationInstance.teardown();
			//webAutomationInstance = null; do not remove this object, we should reuse the existing one between tests because the webDrivers are managed by thread
		}
        if (webAutomationInstance != null) {
        	webAutomationInstance.teardown();
        	webAutomationInstance = null;
        }
		
	}
}

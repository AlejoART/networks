package customapplications;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import com.webpt.WebptATFHandler;

public class PageComponentFactory {
	
	private PageComponentFactory() {	
	}
	public static LoadableComponent getPageObject(Object cls) {
		LoadableComponent instance = (LoadableComponent) cls;
		return PageFactory.initElements(WebptATFHandler.getInstance().getWebAutomation().getWebDriver(), instance.getClass());
	}
}

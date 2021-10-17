package qaautomation.august2021.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasePage {

	String profileText = "//strong[normalize-space()='%s']";

	public ProfilePage(ThreadLocal<WebDriver> driver, ThreadLocal<WebDriverWait> explicitWait) {
		super(driver, explicitWait);
		PageFactory.initElements(driver.get(), this);
	}

	public String getProfileText(String username) {
		By finalXpath = By.xpath(String.format(profileText, username));
		String actualText = getText(finalXpath);
		return actualText;

	}

}

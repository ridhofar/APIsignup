package qaautomation.august2021;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import qaautomation.august2021.pages.LoginPage;
import qaautomation.august2021.pages.ProfilePage;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseWebTest {
	/**
	 * Rigorous Test :-)
	 */
	LoginPage loginPage = new LoginPage(driver, explicitWait);
	ProfilePage profilePage = new ProfilePage(driver, explicitWait);

	@Test(testName = "verify login is success", description = "login will be success")
	public void test() {
		String username = "fullstackdemo";
		String password = "fullstackdemo";

		loginPage.login(username, password);
		String actualUser = profilePage.getProfileText(username);

		assertEquals(actualUser, username);

	}

	@Test(testName = "verify login successful", description = "login will be working fime")
	public void test1() {
		String username = "fullstackdemo";
		String password = "fullstackdemo";
		driver.get().findElement(By.xpath("//a[normalize-space()='Log In/Register As Student']")).click();
		driver.get().findElement(By.id("username")).sendKeys(username);
		driver.get().findElement(By.id("password")).sendKeys(password);
		driver.get().findElement(By.xpath("//button[normalize-space()='Login']")).click();
		String actualText = driver.get()
				.findElement(By.xpath(String.format("//strong[normalize-space()='fullstackdemo']", username)))
				.getText();
		assertEquals(actualText, username);
	}

}

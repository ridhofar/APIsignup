package qaautomation.august2021;

import org.testng.annotations.Test;

import qaautomation.august2021.pages.CommonPage;
import qaautomation.august2021.utils.JavascriptSnippets;
import qaautomation.august2021.utils.TestUtility;

public class SwitchTest extends BaseWebTest {
	CommonPage commonPage = new CommonPage(driver, explicitWait);

	@Test
	public void testSwitchingTab() {
		commonPage.openNewTab();
		commonPage.switchWindow(1);
		commonPage.openUrl("https://facebook.com");

		String script = JavascriptSnippets.alertScript;

		commonPage.runJSCommand(script);
		TestUtility.hardWait(2);
		commonPage.acceptAlert();
		TestUtility.hardWait(2);

	}

	@Test
	public void testForwardBackRefresh() {
		commonPage.openUrl("https://facebook.com");
		commonPage.navigateBrowser("back");
		TestUtility.hardWait(2);
		commonPage.navigateBrowser("forward");
		TestUtility.hardWait(2);
		commonPage.navigateBrowser("refresh");
	}

}

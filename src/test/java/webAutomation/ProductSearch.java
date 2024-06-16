package webAutomation;

import org.testng.annotations.Test;

import base.BasePage;
import base.BaseTest;
import pageObjects.LaunchPage;
import pageObjects.NewTabPage;
import pageObjects.ResultPage;

public class ProductSearch extends BaseTest {

	@Test
	public void searchProduct() {
		LaunchPage launchPage = new LaunchPage();
		launchPage.selectElectronics();
		launchPage.enterInSearchBox();
		launchPage.searchResult();
		launchPage.enterInSearchBoxAgain();
		launchPage.clickOnSearchResult();
		ResultPage resultPage = new ResultPage();
		resultPage.clickOnFirstSearchResult();
		resultPage.switchTabToResult();
		NewTabPage newTabPage = new NewTabPage();
		newTabPage.clickAppleStoreLink();
		newTabPage.clickAppleWatch();
		newTabPage.clickAppleWatchSE();
		newTabPage.scrollToQuickLook();
		newTabPage.hoverOnAppleWatchSEGPS();
		String textOfProductBeforeQuickLook = newTabPage.getTextOfProductBeforeQuickLook();
		BasePage.validate(true, newTabPage.verifyLinkQuickLook(), "Verify Quick Look Link");
		newTabPage.clickOnQuickLook();
		String textOfProductAfterQuickLook = newTabPage.getTextOfProductAfterQuickLook();
		BasePage.validate(textOfProductBeforeQuickLook, textOfProductAfterQuickLook, "Verify Quick Look Link");

	}
}

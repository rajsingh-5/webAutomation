package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import base.BasePage;
import base.BaseTest;

public class NewTabPage extends BasePage {

	By link_AppleStoreLink = By.id("bylineInfo");

	By tab_AppleWatch = By.xpath("//span[text()='Apple Watch']");

	By tab_AppleWatchSE = By.xpath("//span[text()='Apple Watch SE (GPS + Cellular)']");

	By link_AppleWatchSEGPS = By.xpath("//div[@id='4k2av5ugzs']/following::h3");

	By link_AppleWatchSEGPS1 = By.xpath("//a[contains(@title,'[GPS + Cellular 40 mm]')]");

	By link_QuickLook = By.xpath("//span[@class='QuickLook__label__tOBqR']");
//	By link_QuickLook = By.xpath("//div[@class='QuickLook__content__X4jVd']");

	By link_AppleWatchSEGPSAfterQuickLook = By.xpath("//h2/a[contains(@title,'GPS + Cellular 40 mm')]");

	public void clickAppleStoreLink() {
		click(link_AppleStoreLink, "Visit The Apple Store");
	}

	public void clickAppleWatch() {
		clickWithJS(tab_AppleWatch, "Apple Watch");
	}

	public void clickAppleWatchSE() {
		clickWithJS(tab_AppleWatchSE, "Apple Watch");
	}

	public void scrollToQuickLook() {
		try {
			scrollIntoView(link_QuickLook, "Quick Look");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void hoverOnAppleWatchSEGPS() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Actions actions = new Actions(BaseTest.driver);
		actions.moveToElement(BaseTest.driver.findElement(link_AppleWatchSEGPS)).perform();
	}

	public boolean verifyLinkQuickLook() {
		boolean value = isDisplayed(link_QuickLook,"Quick Look");
		return value;
	}

	public void clickOnQuickLook() {
		clickWithJS(link_QuickLook, "Link Quick Look");
	}

	public String getTextOfProductBeforeQuickLook() {
		String text = getAttribute(link_AppleWatchSEGPS1);
		return text;
	}

	public String getTextOfProductAfterQuickLook() {
		String text = getAttribute(link_AppleWatchSEGPSAfterQuickLook);
		return text;
	}

}

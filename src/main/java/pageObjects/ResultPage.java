package pageObjects;

import org.openqa.selenium.By;

import base.BasePage;

public class ResultPage extends BasePage {

	By link_FirstResult = By.xpath("(//span[contains(text(),'Apple iPhone 13')])[1]");

	public void clickOnFirstSearchResult() {
		click(link_FirstResult, "Apple iPhone 13");
	}
	
	public void switchTabToResult() {
		switchToTab("Apple iPhone 13");
	}
}

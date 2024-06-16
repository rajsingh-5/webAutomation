package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import base.BasePage;
import base.BaseTest;

public class LaunchPage extends BasePage {

	By dropdown_Electronics = By.id("searchDropdownBox");

	By listOfItem = By.xpath("//div[@class='s-suggestion s-suggestion-ellipsis-direction']");

	By searchBox = By.xpath("//input[@id='twotabsearchtextbox']");

	By searchResult = By.xpath("//div[@aria-label='iphone 13 128 gb']");

	public void selectElectronics() {
		selectFromDropDown(dropdown_Electronics, "Electronics");
	}

	public void enterInSearchBox() {
		Actions action = new Actions(BaseTest.driver);
		action.sendKeys("IPhone 13").perform();
	}

	public void searchResult() {
		allElementInList(listOfItem, "iphone 13");
	}

	public void enterInSearchBoxAgain() {
		sendKeys(searchBox, "IPhone 13 128 GB");
	}

	public void clickOnSearchResult() {
		click(searchResult, "iPhone 13 128 GB");
	}

}

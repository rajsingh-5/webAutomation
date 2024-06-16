package utils;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
	public WebDriverWait wait;

	public WebDriverUtility(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void waitForElemnetToBeVisible(WebElement elemnet) {
		wait.until(ExpectedConditions.visibilityOf(elemnet));
	}
	public void waitForElemnetToBeClickable(By by) {
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

}

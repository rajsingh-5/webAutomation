package base;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import utils.ReporterClass;
import utils.WebDriverUtility;

public class BasePage {
	public WebDriverUtility webDriverUtility = new WebDriverUtility(BaseTest.driver);

	public void click(By by, String elementName) {
		try {
			WebElement element = BaseTest.driver.findElement(by);
			webDriverUtility.waitForElemnetToBeClickable(by);
			element.click();
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "\"" + elementName + "\" is clicked successfully");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			LogManager.getLogger().info(elementName + " unable to click");
			ReporterClass.ReportLoggerScreenshot(ReporterClass.info, elementName + " unable to click");
			throw e;
		}
	}

	public void clickWithJS(By by, String elementName) {
		try {
			WebElement element = BaseTest.driver.findElement(by);
//			webDriverUtility.waitForElemnetToBeClickable(by);
			((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].click();", element);
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "\"" + elementName + "\" is clicked successfully");
			LogManager.getLogger().info(elementName + " is clicked");
		} catch (Exception e) {
			LogManager.getLogger().info(elementName + " unable to click");
			ReporterClass.ReportLoggerScreenshot(ReporterClass.info, elementName + " unable to click");
			throw e;
		}
	}

	public void scrollIntoView(By by, String elementName) throws Exception {
		try {
			Thread.sleep(5000);
			WebElement element = BaseTest.driver.findElement(by);
//			webDriverUtility.waitForElemnetToBeClickable(by);
			((JavascriptExecutor) BaseTest.driver).executeScript("arguments[0].scrollIntoView(true);", element);
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "\"" + elementName + "\" is in view.");
			LogManager.getLogger().info(elementName + " is in view");
		} catch (Exception e) {
			LogManager.getLogger().info(elementName + " unable to scroll");
			ReporterClass.ReportLoggerScreenshot(ReporterClass.info, elementName + " unable to scroll");
			throw e;
		}
	}

	public void sendKeys(By by, String txt) {
		WebElement element = BaseTest.driver.findElement(by);
		webDriverUtility.waitForElemnetToBeVisible(element);
		try {
			element.clear();
			element.sendKeys(txt);
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "text \"" + txt + "\" is entered in text box");
			LogManager.getLogger().info("text " + txt + " is entered in text box");
		} catch (Exception e) {
			BaseTest.test.info("send Keys " + txt + "  is failed");
			LogManager.getLogger().info("send Keys " + txt + " is failed");
			Assert.fail();
		}
	}

	public boolean isDisplayed(By by, String text) {
		try {
			new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.presenceOfElementLocated(by));
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass,
					"text \"" + text + "\" is displayed successfully.");
			LogManager.getLogger().info(text + " is displayed successfully.");
			return true;
		} catch (Exception e) {
			BaseTest.test.fail("Element is not present");
			throw e;
		}
	}

	public void selectFromDropDown(By by, String value) {
		try {
			WebElement element = BaseTest.driver.findElement(by);
			new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.presenceOfElementLocated(by));
			Select select = new Select(element);
			select.selectByVisibleText(value);
			ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, "\"" + value + "\" is selected successfully");
			LogManager.getLogger().info(value + " is selected");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LogManager.getLogger().info(value + " unable to click");
			ReporterClass.ReportLoggerScreenshot(ReporterClass.info, value + " unable to selected");
			throw e;
		}
	}

	public void allElementInList(By by, String value) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<WebElement> element = new WebDriverWait(BaseTest.driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		for (WebElement ele : element) {
			try {
				String text = ele.getText();
				if (text.contains(value)) {
					ReporterClass.ReportLoggerScreenshot(ReporterClass.pass, text + " is verified successfully");
					LogManager.getLogger().info(text + " is verified ");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void switchToTab(String windowName) {
		Set<String> handles = BaseTest.driver.getWindowHandles();
		for (String string : handles) {
			BaseTest.driver.switchTo().window(string);
			String window = BaseTest.driver.getTitle();
			if (window.contains(windowName)) {
				break;
			}
		}
	}

	public String getAttribute(By by) {
		WebElement element = BaseTest.driver.findElement(by);
		webDriverUtility.waitForElemnetToBeVisible(element);
		String text = element.getAttribute("title");
		LogManager.getLogger().info(text + " is fetched successfully.");
		return text;
	}

	public static void validate(Object expected, Object actual, String assertionFor) {
		try {
			logFile(assertionFor, actual, expected);
			Assert.assertEquals(actual, expected, assertionFor);
			ReporterClass.pass("<b> <u>" + assertionFor + "</u> </b>   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected, true);
			LogManager.getLogger()
					.info(" - " + assertionFor + "  |   Actual: " + actual + " and  Expected: " + expected, true);

		} catch (AssertionError assertionError) {
			ReporterClass.fail("<b> <u>" + assertionFor + "   |   <b><i>Actual: </i> </b>" + actual
					+ " and <b><i> Expected: </i> </b>" + expected, true);
			LogManager.getLogger()
					.info(" - " + assertionFor + "   |   Actual: " + actual + " and  Expected: " + expected, true);
			Assert.fail(assertionFor);
		}
	}

	private static void logFile(String assertionFor, Object actual, Object expected) {
		Reporter.log("Checking:" + assertionFor, true);
		Reporter.log("Expected: " + expected, true);
		Reporter.log("Actual: " + actual, true);
	}

}

package base;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Test {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");
		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchDropdownBox")));
		WebElement findElement = driver.findElement(By.id("searchDropdownBox"));
		Select select = new Select(findElement);
		select.selectByVisibleText("Electronics");
		Thread.sleep(1000);
		Actions action = new Actions(driver);
		action.sendKeys("IPhone 13").perform();
		List<WebElement> elements = driver.findElements(By.xpath("//div[@class=\"left-pane-results-container\"]/div"));
		for (WebElement webElement : elements) {
			System.out.println(webElement.getText());
		}
	}
}

package base;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.FileUtility;
import utils.PropertyUtils;

public class BaseTest {
	public static FileUtility fLib = new FileUtility();
	public SoftAssert softAssert = new SoftAssert();
	public static WebDriver driver;
	public String currentRunningMethod;

	ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;
	public static String className;

	@BeforeSuite
	public void beforeSuite() {
	}

	@BeforeClass
	public void configBc(ITestContext context) throws Throwable {
		LogManager.getLogger().info("Runnig Before Class");
		className = this.getClass().getSimpleName();
	}

	@BeforeMethod
	public void configBm(ITestResult result) throws Throwable {

		currentRunningMethod = result.getMethod().getMethodName();
		spark = new ExtentSparkReporter("./Extent Report/" + dateForReport() + "/" + className + "/"
				+ currentRunningMethod + dateTime() + ".html");
		spark.config().setDocumentTitle("General Store");
		spark.config().setReportName(className + " Automation Execution report");
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("os", System.getProperty("os.name"));

		test = report.createTest(result.getMethod().getMethodName());
		System.out.println("starting: " + result.getMethod().getMethodName());
		LogManager.getLogger().info("starting: " + result.getMethod().getMethodName());

		driver = initDriver(PropertyUtils.getTestData("browser"));
		driver.get(PropertyUtils.getTestData("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterMethod
	public void configAm(ITestResult result) throws Exception {
		LogManager.getLogger().info(
				"---------------------------Execution of the Method is Finished--------------------------------------\n \n");
		report.flush();
		driver.quit();
	}

	public static WebDriver initDriver(String browserName) {
		WebDriver driver = null;
		switch (browserName.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			driver = new ChromeDriver(options);
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		default:
			break;
		}
		return driver;
	}

	public String dateTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_dd-MM-yyyy_HHmmss");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

	public String dateForReport() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		LocalDateTime now = LocalDateTime.now();
		return dtf.format(now);
	}

}

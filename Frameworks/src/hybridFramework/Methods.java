package hybridFramework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Methods {
	static WebDriver driver;
	static String minPrice;
	static String flightName;

	public static void openBrowser() {
		driver = new ChromeDriver();
	}

	public static void maximizeBrowser() {
		driver.manage().window().maximize();
		//Added a new comment in line number 23.
	}

	public static void implementWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public static void navigateTo(String baseUrl) {
		driver.get(baseUrl);
	}

	public static void clickFlightTab() {
		driver.findElement(By.id("tab-flight-tab")).click();
	}

	public static void enterSourceCity(String value, String param) {
		driver.findElement(By.id(value)).clear();
		driver.findElement(By.id(value)).sendKeys(param);
	}

	public static void enterDestinationCity(String value, String param) {
		driver.findElement(By.id(value)).clear();
		driver.findElement(By.id(value)).sendKeys(param);
	}

	public static void selectData(String applicationName) {
		if (applicationName.equals("Expedia")) {
			driver.findElement(By.id("flight-departing")).click();
			WebElement comingMonth = driver
					.findElement(By.cssSelector("#flight-departing-wrapper > div > div > div:nth-child(5)"));
			List<WebElement> columns = comingMonth.findElements(By.tagName("button"));
			for (WebElement x : columns) {
				if (x.getText().equals("26")) {
					x.findElement(By.xpath("(//button[contains(text(),'26')])[2]")).click();
					break;
				}
			}
		}

		else if (applicationName.equals("MakeMyTrip")) {
			driver.findElement(By.id("hp-widget__depart")).click();
			WebElement comingMonth = driver.findElement(By.xpath("(//div[contains(@class,'last')])[1]"));
			ArrayList<WebElement> columns = (ArrayList<WebElement>) comingMonth.findElements(By.tagName("a"));
			for (WebElement x : columns) {
				if (x.getText().equals("26")) {
					x.findElement(By.xpath("(//a[contains(text(),'26')])[2]")).click();
					break;
				}
			}
		}
	}

	public static void clickSearchButton(String value) {
		driver.findElement(By.id(value)).click();
	}

	public static void fetchMinimumPrice(String locator, String value) {
		if (locator.equals("xpath")) {
			minPrice = driver.findElement(By.xpath(value)).getText();
		}

		else if (locator.equals("cssSelector")) {
			minPrice = driver.findElement(By.cssSelector(value)).getText();
		}
	}

	public static void fetchFlightName(String locator, String value) {
		if (locator.equals("xpath")) {
			flightName = driver.findElement(By.xpath(value)).getText();
		}

		else if (locator.equals("cssSelector")) {
			flightName = driver.findElement(By.cssSelector(value)).getText();
		}
	}

	public static void printDetails(String applicationName,String source,String destination) {
		System.out.println("Minimum price of ticket on " + applicationName + " from " + source + " to " + destination + " is Rs " + minPrice
				+ " and is being offered by " + flightName);
	}

	public static void closeBrowser() {
		driver.close();
	}

}

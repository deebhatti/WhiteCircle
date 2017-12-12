package demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utils.ReadExcel;

public class MakeMyTrip {
	WebDriver driver;
	
	@BeforeTest
	public void openBrowser(){
		driver = new ChromeDriver();
		//New Comment
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.makemytrip.com/");
	}
	
	@Test
	public void bookFlight() throws IOException{
		//Data getting transferred from Excel sheet to a 2D-Array
		String[][] data = ReadExcel.getData("TestData.xlsx", "Sheet1");
		
		//We are going to test the application again and again
		for(int i=1; i<6;i++){
			
			String source = data[i][1];
			String destination = data[i][2];
			
			//Entering the source city
			driver.findElement(By.id("hp-widget__sfrom")).clear();
			driver.findElement(By.id("hp-widget__sfrom")).sendKeys(source);
			
			//Entering the destination city
			driver.findElement(By.id("hp-widget__sTo")).clear();
			driver.findElement(By.id("hp-widget__sTo")).sendKeys(destination);
			
			//Selecting required date from the calendar
			if(i==1){
				driver.findElement(By.id("hp-widget__depart")).click(); 			
				WebElement comingMonth= driver.findElement(By.xpath("(//div[contains(@class,'last')])[1]"));			
				ArrayList<WebElement> columns = (ArrayList<WebElement>) comingMonth.findElements(By.tagName("a"));					
				for(WebElement x : columns){
					if(x.getText().equals("26")){
						x.findElement(By.xpath("(//a[contains(text(),'26')])[2]")).click();
						break;
					}
				}
			}
			
			
			//Click on Search button
			driver.findElement(By.cssSelector("#searchBtn")).click(); 
			
			//Fetching the minimum price from the web-application
			String minPrice = driver.findElement(By.cssSelector("#content > div > div.container.ng-scope > div.row > div.main.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div:nth-child(5) > div > div.top_first_part.clearfix > div.col-lg-2.col-md-2.col-sm-2.col-xs-4.text-right.price_sectn.make_relative.pad0 > p.price_info.RobotoRegular > span.num.ng-binding")).getText();
			
			//Fetching the name of the airline which is offering minimum price
			String airline = driver.findElement(By.cssSelector("#content > div > div.container.ng-scope > div.row > div.main.col-lg-9.col-md-9.col-sm-12.col-xs-12 > div:nth-child(5) > div > div.top_first_part.clearfix > div.col-lg-2.col-md-2.col-sm-2.col-xs-2.logo_section.padR0 > span.block.append_bottom3.clearfix > span.pull-left.airline_info_detls > span.logo_name.append_bottomSpace6.hidden-xs.visible-stb.ng-binding.ng-scope")).getText();
			
			//Printing minimum price and the flight name
			System.out.println("Minimum price from " + source + " to " + destination + " is  = Rs." + minPrice + " and is being offered by " + airline);
			
			//Take us back by 1 Page
			driver.navigate().back();
		}
	}

}

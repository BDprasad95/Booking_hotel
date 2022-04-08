package Tests;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookMyRoom {

	public WebDriver driver;
	public static ExtentReports extent;
	public static ExtentSparkReporter reports;
	
	@Given("Launch browser")
	public void launch_browser() {
		
		System.out.println(" === Inside Browser === ");
		System.setProperty("webdriver.chrome.driver", "C:/Users/Admin/eclipse-workspace/MyHotel/Drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		

		extent = new ExtentReports();
		reports = new ExtentSparkReporter("TestReport.html");
		extent.attachReporter(reports);
	}

	@When("on Page and title verfication")
	public void on_page_and_title_verfication() throws Exception {
		
		driver.get("https://www.booking.com/index.html?aid=2200931");
		ExtentTest test = extent.createTest("Checking Title");
		
		if(driver.getTitle().equals("Booking.com | Official site | The best hotels, flights, car rentals & accommodations"))
		{
			test.log(Status.PASS, "Title matched");
			test.pass(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
		}
		else
		{
			test.fail(MediaEntityBuilder.createScreenCaptureFromPath("img.png").build());
		}
		
	}

	@When("Enter Location to travel")
	public void enter_location_to_travel() throws InterruptedException {
		
		
		// ExtentTest test = extent.createTest("Checking Input Box Displayed Or Not");
		WebElement location = driver.findElement(By.id("ss"));
		location.sendKeys("Rajahmundry, Andhra Pradesh, India");
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[1]/div[1]/div[1]/ul[1]/li[1]")).click();
		System.out.println(" === Entered location === ");
		ExtentTest test = extent.createTest("Location Entered");
		
		if(location.isDisplayed())
		{
			test.log(Status.PASS, "Enterd Location");
		}
		else
		{
			test.fail("Failed to enter location");
		}
	   
	}

	@When("Enter CheckIN Date")
	public void enter_check_in_date() throws Exception {
		Thread.sleep(6000);
		ExtentTest test = extent.createTest("CheckIn Date");
		List<WebElement> ele = driver.findElements(By.xpath("//*[@id=\"frm\"]/div[1]/div[2]/div[2]/div/div/div[3]/div[2]/table/tbody/tr/td/span/span"));
		
		for(WebElement mydate:ele)
		{
			String Date = mydate.getText();
			if(Date.equalsIgnoreCase("28"))
			{
				test.log(Status.PASS, "Selected CheckIn and CheckOut Date");
				mydate.click();
				break;
			}
			else
			{
				test.log(Status.FAIL, "Selected CheckIn and CheckOut Date");
			}
		}
	  	
	}

	@When("Enter Persnol Details")
	public void enter_persnol_details() throws InterruptedException {

		Thread.sleep(5000);
		ExtentTest test = extent.createTest("Family-details");
		WebElement Checkin = driver.findElement(By.xpath("//*[@id=\"xp__guests__toggle\"]"));

		if(Checkin.isDisplayed())
		{
			Checkin.click();
			Thread.sleep(7000);
			// click on adults + 
			driver.findElement(By.xpath("//button[@aria-label='Increase number of Adults']")).click();
			Thread.sleep(5000);
			// Click on child + 
			driver.findElement(By.xpath("(//button[@aria-label='Increase number of Children'])[1]")).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/select")).click();
			List<WebElement> agelist = driver.findElements(By.xpath("//*[@id=\"xp__guests__inputs-container\"]/div/div/div[3]/select/option"));
			for(WebElement allages:agelist)
			{
				String Age = allages.getText();
				
				if(Age.equalsIgnoreCase("7 years old"))
				{
					test.log(Status.PASS, "Family Details Location Identified");
					allages.click();
					break;
				}
				else
				{
					test.fail("Failed To Identified Family Details Location");
				}
			}
			
			System.out.println(" === Entered Family Location === ");
		}
		Thread.sleep(5000);
		
	}

	@Then("Click to search rooms on that location")
	public void click_to_search_rooms_on_that_location() throws InterruptedException {
		
		ExtentTest test = extent.createTest("Family-details");
		test.log(Status.PASS, "Searched Hotels");
		WebElement Checkin = driver.findElement(By.xpath("//*[@id=\"frm\"]/div[1]/div[4]/div[2]/button"));
		Thread.sleep(5000);
		
		Checkin.click();
		System.out.println(" === Entered SearchButton === ");
		Thread.sleep(8000);
	
		extent.flush();
		driver.close();
		
	}
}

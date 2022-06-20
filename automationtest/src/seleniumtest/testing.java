
package seleniumtest;

import static org.testng.AssertJUnit.assertTrue;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class testing {
	
	
	
	WebDriver driver;

	
	@BeforeTest
	public void launchBrowser() {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\user\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver= new ChromeDriver();
		driver.get("http://www.cleartrip.com");
		driver.manage().window().maximize(); 
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
        
        try {
			selectDetails();
			verifyErrorMessage();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
      
	}
	
	@Test
	public void selectDetails() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='p-relative']//input")).click();
		
		Thread.sleep(3000);
		
		//select the from test field and inputting nig
		driver.findElement(By.xpath("//input[contains(@class,'field bw-1')]")).sendKeys("nig");
		
		
		
		//select lagos nigeria
		driver.findElement(By.xpath("//p[text()='Lagos, NG - Murtala Muhammed (LOS)']")).click();
		
		//select the to field and entering ind
		driver.findElement(By.cssSelector("div#root>div>div>div>div>div:nth-of-type(2)>div>div:nth-of-type(4)>div>div:nth-of-type(5)>div>div>div>input")).sendKeys("ind");
		
		//select ind
		//driver.findElement(By.xpath("//p[text()='Indore, IN - Devi Ahilya Bai Holkar (IDR)']")).click();
		driver.findElement(By.xpath("//li[contains(@class,'ls-reset br-4')]")).click();
		/// picking date
		
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,350)", "");
		
	
		Thread.sleep(3000);
	
		driver.findElement(By.xpath("//div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[4]/div[3]/div[1]/div[1]/div[1]/div[1]/button[1]")).click();
		driver.findElement(By.xpath("(//div[text()='3'])[2]")).click();
		
		
		
		//picking adult
		WebElement adults = driver.findElement(By.xpath("//div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[5]/div[1]/div[1]/div[1]/div[1]/select[1]")); 
		new Select(adults).selectByIndex(2); 
		
		//picking children
		WebElement children = driver.findElement(By.xpath("(//select[contains(@class,'default-select bc-neutral-100')])[2]")); 
		new Select(children).selectByIndex(2);
		
		// more options
		driver.findElement(By.xpath("//strong[text()='More options:']")).click();
		
		//class of flight
		WebElement classOfTra = driver.findElement(By.xpath("//div[@id='root']/div[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[7]/div[1]/div[1]/div[1]/select[1]")); 
		new Select(classOfTra).selectByValue("First"); 
		
		// airways
		driver.findElement(By.xpath("//input[@placeholder='Airline name']")).sendKeys("Qatar Airways");

		
		//select enter
		driver.findElement(By.xpath("//button[text()='Search flights']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(3000));
		
		
		
			
	}
	
	@AfterTest
	public void verifyErrorMessage () {
		
		// asserting text
		String actualMsg = driver.findElement(By.xpath("//p[contains(@class,'fs-2 mt-2')]")).getText();
		String expectedString = "Please reset your filters to see flights";

			
		if(actualMsg.contains(expectedString)) 
		{
			System.out.println("Test Case Passed");
		}else
		{
			System.out.println("Test Case Failed");
		};
		
		//WebDriverWait wait = new Webdriver wait(driver, 3);
	}
		
	
		
	public static void main(String [] args) {
			testing obj = new testing();
			obj.launchBrowser();
		
	}
}

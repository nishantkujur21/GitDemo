package nishantSelenium.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateLoginEcom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--incognito");

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver(options);
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();


		String firstName= "Nishant";
		String lastName = "Kujur";
		String userEmail= "nkrforever13@gmail.com";
		String userMobile= "9971838669";
		String userPassword = "Test112233";


		driver.findElement(By.cssSelector("p[class='login-wrapper-footer-text']")).click();
		driver.findElement(By.id("firstName")).sendKeys(firstName);
		driver.findElement(By.id("lastName")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@formcontrolname='userEmail']")).sendKeys(userEmail);
		driver.findElement(By.id("userMobile")).sendKeys(userMobile);

		//for occupation select button
		WebElement occupationSelect = driver.findElement(By.cssSelector("select[class*='custom-select']"));
		Select btn= new Select(occupationSelect);
		btn.selectByVisibleText("Engineer");

		driver.findElement(By.cssSelector("input[value='Male']")).click();
		driver.findElement(By.id("userPassword")).sendKeys(userPassword);
		driver.findElement(By.id("confirmPassword")).sendKeys(userPassword);
		driver.findElement(By.cssSelector("input[type='checkbox']")).click();
		driver.findElement(By.id("login")).click();


	}

}

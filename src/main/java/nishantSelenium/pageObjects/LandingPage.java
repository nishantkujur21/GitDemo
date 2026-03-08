package nishantSelenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nishantSelenium.abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

		WebDriver driver;

		public LandingPage(WebDriver driver) {

			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}


		@FindBy(id="userEmail")
		WebElement userEmailElement;

		@FindBy(id="userPassword")
		WebElement userPasswordElement;

		@FindBy(id="login")
		WebElement loginButtonElement;
		

		public  ProductCatalogue loginEcomApplication(String userEmail, String userPassword) {

			userEmailElement.sendKeys(userEmail);
			userPasswordElement.sendKeys(userPassword);
			loginButtonElement.click();
			
			ProductCatalogue productPage= new ProductCatalogue(driver);
			return productPage;
		}

		public void goToEcomLink() {
			driver.get("https://rahulshettyacademy.com/client/");
		}

		@FindBy(css="div[class*='flyInOut']")
		WebElement ErrorMessage;

		public String getErrorMessage(){

			waitForElementsToAppear(ErrorMessage);
			System.out.println(ErrorMessage.getText());
			return ErrorMessage.getText();
		}
	}



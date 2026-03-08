 package nishantSelenium.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nishantSelenium.abstractComponents.AbstractComponent;

public class PaymentPage extends AbstractComponent {

		WebDriver driver;

		public PaymentPage(WebDriver driver) {

			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}



		@FindBy(css="button[class='ta-item list-group-item ng-star-inserted']")
		List<WebElement> filterCountryList;
		public WebElement selectEnteredCountryName(String userCountryName) {
			for(WebElement country:filterCountryList) {
				String countryText= country.getText();
				if(countryText.equalsIgnoreCase(userCountryName)) {
					System.out.println("Country selected : " +countryText);
					return country;
					}

				}
			System.out.println("No Matching Country for " +userCountryName);
			return null;
		}


		@FindBy(css="input[placeholder='Select Country']")
		WebElement selectCountry;

		@FindBy(css="button[class='ta-item list-group-item ng-star-inserted']:nth-child(3)")
		WebElement filterCountry;



		public void enterRequiredDetails(String userCountryName) {


			selectCountry.sendKeys(userCountryName);
			waitForElementsToAppear(filterCountry);
			selectEnteredCountryName(userCountryName).click();
			}

		@FindBy(css=".btnn.action__submit")
		WebElement placeOrderButton;

		public ConfirmationPage placeOrder() {
			placeOrderButton.click();
			ConfirmationPage confirmationPage= new ConfirmationPage(driver);
			return confirmationPage;

		}

}







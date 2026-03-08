package nishantSelenium.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nishantSelenium.abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

		WebDriver driver;

		public ConfirmationPage(WebDriver driver) {

			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(css=".hero-primary")
		WebElement confirmationMessage;

		public boolean getConfirmationMessage(String orderConfirmMessage) {

		String orderMessage = confirmationMessage.getText();
		System.out.println(orderMessage);
		orderMessage.equalsIgnoreCase(orderConfirmMessage);
		return true;
		}






}










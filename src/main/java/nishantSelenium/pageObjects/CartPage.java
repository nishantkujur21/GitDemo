package nishantSelenium.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nishantSelenium.abstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

		WebDriver driver;

		public CartPage(WebDriver driver) {

			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}

		@FindBy(css=".cartSection h3")
		List<WebElement>cartProducts;
		
		@FindBy(css=".cartSection p")
		WebElement cartProduct;
		
		public String verifyProductDisplay(String targetProductName) {
			
			waitForElementsToAppear(cartProduct);
			for(WebElement cartProduct: cartProducts) 
			{
				String cartProductText = cartProduct.getText();
				if(cartProductText.equalsIgnoreCase(targetProductName)) {
					System.out.println("cart Product Name : " +cartProductText);
					return cartProductText;
				}
			}
			return null;
		}
	


		@FindBy(xpath= "//button[text()='Checkout']")
		WebElement checkoutIcon;
		public PaymentPage goToCheckoutPage() {
			checkoutIcon.click();
			PaymentPage paymentPage = new PaymentPage(driver);
			return paymentPage;
		}


	}



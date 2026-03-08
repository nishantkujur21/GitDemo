package nishantSelenium.tests;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import nishantSelenium.pageObjects.CartPage;
import nishantSelenium.pageObjects.ProductCatalogue;
import nishantSelenium.testComponents.BaseTest;
import nishantSelenium.testComponents.RetryFailTest;

public class ErrorCredentialValidationTest extends BaseTest {


	@Test(groups="ErrorHandling", retryAnalyzer = RetryFailTest.class)
	public void LoginErrorValidation()  {

		//Credentials
		String userEmail= "nkrforever13@gmail.com";
		String userPassword = "Test";
		String errorMessage = "Incorrect email or password.";

		landingPage.loginEcomApplication( userEmail,  userPassword);
		Assert.assertEquals(landingPage.getErrorMessage(), errorMessage);
		}



	@Test
	public void ProductErrorValidationCartPage() {

	String targetProductName= "ZARA COAT 3";

	//only for testing
	String cartProductforTest ="ZARA COAT 3";

	//Credentials
			String userEmail= "nkrforever13@gmail.com";
			String userPassword = "Test112233";


		//Entering credentials
		ProductCatalogue productPage= landingPage.loginEcomApplication(userEmail, userPassword);

		//Finding products
		List<WebElement> productList= productPage.getProductList();

		//adding the product to the cart
		productPage.addProductToCart(targetProductName);

		//clicking the cart icon
		CartPage cartPage = productPage.goToCartPage();

		String cartProductFound = cartPage.verifyProductDisplay(targetProductName);
		//for failing the test
		Assert.assertEquals(cartProductFound, cartProductforTest);



	}
}





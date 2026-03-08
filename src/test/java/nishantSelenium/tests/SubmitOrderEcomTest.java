	package nishantSelenium.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import nishantSelenium.pageObjects.CartPage;
import nishantSelenium.pageObjects.ConfirmationPage;
import nishantSelenium.pageObjects.OrderPage;
import nishantSelenium.pageObjects.PaymentPage;
import nishantSelenium.pageObjects.ProductCatalogue;
import nishantSelenium.testComponents.BaseTest;

public class SubmitOrderEcomTest extends BaseTest {


	//Credentials
			String userCountryName = "India";
			String orderConfirmMessage = "Thankyou for the order.";

	@Test(dataProvider="getData" , groups ={"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException {

		//Entering credentials
		ProductCatalogue productPage= landingPage.loginEcomApplication(input.get("userEmail"), input.get("userPassword"));

		productPage.successfulLogin();
		//Finding products
		productPage.getProductList();

		//adding the product to the cart
		productPage.addProductToCart(input.get("targetProductName"));

		//clicking the cart icon
		CartPage cartPage = productPage.goToCartPage();

		String cartProductText = cartPage.verifyProductDisplay(input.get("targetProductName"));
		Assert.assertEquals(cartProductText, input.get("targetProductName"));

		PaymentPage paymentPage = cartPage.goToCheckoutPage();
		paymentPage.enterRequiredDetails(userCountryName);
		ConfirmationPage confrimationPage = paymentPage.placeOrder();

		boolean messageFound = confrimationPage.getConfirmationMessage(orderConfirmMessage);
		Assert.assertTrue(messageFound);

			}


	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() throws InterruptedException {

		String userEmail = "nkrforever13@gmail.com";
		String userPassword = "Test112233";
		String targetProductName = "ZARA COAT 3";

		//Entering credentials
		ProductCatalogue productPage= landingPage.loginEcomApplication(userEmail, userPassword);
		productPage.successfulLogin();
		OrderPage orderPage = productPage.goToOrderPage();
		String orderProductFound = orderPage.verifyOrderPageDisplay(targetProductName);
		Assert.assertEquals(targetProductName, orderProductFound);

	}



	@DataProvider
	public Object [][] getData() throws IOException
	{
//		HashMap<String ,String> map= new HashMap <String , String> ();
//		map.put("userEmail" ,"nkrforever13@gmail.com" );
//		map.put("userPassword" ,"Test112233" );
//		map.put("targetProductName" ,"ZARA COAT 3" );
//
//		HashMap<String ,String> map1= new HashMap <String , String> ();
//		map1.put("userEmail" ,"nkrforever13@test.com" );
//		map1.put("userPassword" ,"Test112233" );
//		map1.put("targetProductName" ,"ADIDAS ORIGINAL" );

		List<HashMap<String, String>> data =  getJsonDataToMap(System.getProperty("user.dir")
				+"//src//test//java//nishantSelenium//data//PurchaseOrder.json") ;
		return new Object[][] {{data.get(0)}}; //{data.get(1)}};

	}


//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"nkrforever13@gmail.com", "Test112233", "ZARA COAT 3"},
//			{"nkrforever13@test.com", "Test112233", "ADIDAS ORIGINAL"}};
//	}


}





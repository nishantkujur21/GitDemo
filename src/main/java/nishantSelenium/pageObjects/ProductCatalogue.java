package nishantSelenium.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nishantSelenium.abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

		WebDriver driver;

		public ProductCatalogue(WebDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}


		@FindBy(css="div[class*='col-lg-4']")
		List<WebElement> productList;

		@FindBy(xpath="//div[contains(@class,'col-lg-4')]")
		WebElement product;

		public List<WebElement> getProductList() {

			waitForElementsToAppear(product);
			return productList;
		}


		By productText = By.cssSelector("b");

		public WebElement getProductByName(String targetProductName) {

			for(WebElement product:productList)
			{
				String productNameFound= product.findElement(productText).getText();
				if (productNameFound.equalsIgnoreCase(targetProductName)){
					System.out.println("Product Found : " +productNameFound);
					return product;
				}
				}
			System.out.println("No matching product found for : " +targetProductName);
			return null;
		}


		
		@FindBy(css=".toast-container")
		WebElement toasterMessage;

		
		By addToCart = By.cssSelector("i[class*='fa fa-shopping-cart']");

		public void addProductToCart(String targetProductName) {

			WebElement product = getProductByName(targetProductName);
			
			   if(product != null) {
			        // Use the scroll method
			        scrollToElement(product);
			        
			        WebElement addToCartButton = product.findElement(addToCart);
			        
			        // Use JavaScript click
			        JavascriptExecutor js = (JavascriptExecutor) driver;
			        js.executeScript("arguments[0].click();", addToCartButton);
			        
			        waitForElementsToAppear(toasterMessage);  // ← add this
			        waitForElementsToDisappear(toasterMessage);
			        
			
			// Scroll back to top after adding to cart
			js.executeScript("window.scrollTo(0, 0);");
			// Small wait after scrolling to top
			try { 
				Thread.sleep(300); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		}

		
		public void successfulLogin() {
			  
	        waitForElementsToAppear(toasterMessage);  // ← add this
	        waitForElementsToDisappear(toasterMessage);
		}
		
}



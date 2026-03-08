package nishantSelenium.abstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nishantSelenium.pageObjects.CartPage;
import nishantSelenium.pageObjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}



	public void waitForElementsToAppear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}

	public void waitForElementsToDisappear(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOf(findBy));

	}
	
	
	public void WaitForElementsToBeClickable(WebElement findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));

	}

	@FindBy(css=".toast-container")
	WebElement toasterMessage;
	
	@FindBy(css="div[class*='ngx-spinner-overlay']")
	WebElement spinner ;

	@FindBy(css="button[routerlink*='cart']")
	WebElement cartIcon;

	public CartPage goToCartPage() {
		waitForElementsToDisappear(spinner);
		WaitForElementsToBeClickable(cartIcon);
		cartIcon.click();
		CartPage cartPage =  new CartPage(driver);
		return cartPage;
		}


	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement orderIcon;

	public OrderPage goToOrderPage() {
		waitForElementsToDisappear(spinner);
		WaitForElementsToBeClickable(orderIcon);
		orderIcon.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;


	}
	//for scrolling after new UI for the automation testing
	public void scrollToElement(WebElement element) {
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
	    
	    // Optional: wait after scroll
	    try { 
	        Thread.sleep(500); 
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	}

}

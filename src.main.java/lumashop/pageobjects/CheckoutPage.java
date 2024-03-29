package lumashop.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import lumashop.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	protected WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "input[name='street[0]']")
	private WebElement addressInput;

	@FindBy(css = "input[name='city']")
	private WebElement cityInput;

	@FindBy(css = "input[name='postcode']")
	private WebElement postCodeInput;

	@FindBy(css = "input[name='telephone']")
	private WebElement telephoneInput;

	@FindBy(css = ".continue")
	private WebElement continueButton;

	@FindBy(css = "input[value='flatrate_flatrate']")
	private WebElement flatRateRadioButton;

	@FindBy(css = "select[name='country_id']")
	private WebElement selectCountry;

	@FindBy(css = "select[name='region_id']")
	private WebElement selectState;

	@FindBy(css = "//*[text()='Place Order']")
	private WebElement placeOrderButton;

	@FindBy(css = "//*[text()='Thank you for your purchase!']")
	private WebElement successMessage;
	
	@FindBy(xpath = "//*[text()='Place Order']")
	private WebElement submit;

	public void addAddress(String address) throws InterruptedException {

		Actions a = new Actions(driver);
		Thread.sleep(6000);
		a.moveToElement(addressInput).build().perform();
		a.sendKeys(addressInput, address).build().perform();
	}

	public void selectCountry(String country) {

		selectCountry.click();
		Select dropdown = new Select(selectCountry);
		dropdown.selectByVisibleText(country);
	}

	public void selectState(String state) {

		selectState.click();
		Select dropdown2 = new Select(selectState);
		dropdown2.selectByVisibleText(state);
	}

	public void addCity(String city) {
		Actions a = new Actions(driver);
		a.sendKeys(cityInput, city).build().perform();

	}

	public void addPostCode(String postCode) {
		Actions a = new Actions(driver);
		a.sendKeys(postCodeInput, postCode).build().perform();
	}

	public void addTelephoneNumber(String telephone) {
		Actions a = new Actions(driver);
		a.sendKeys(telephoneInput, telephone).build().perform();
	}

	public void selectShippingRate() {

		flatRateRadioButton.click();
		continueButton.click();
	}

	
	public ConfirmationPage submitOrder() throws InterruptedException {
		
		Thread.sleep(3000);
		submit.click();
		return new ConfirmationPage(driver);
	}
	
	
	
	
	
	
	
	
	
	
	
}

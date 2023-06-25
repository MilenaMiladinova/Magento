package lumashop.tests;

import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.auto.common.Visibility;

import io.github.bonigarcia.wdm.WebDriverManager;
import lumashop.pageobjects.AccountCreationPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String firstName = "George";
		String lastName = "King";
		String email = "gk1635009788@gmail.com";
		String address = "162 Blue Spring Ave.";
		String city = "Deltona";
		String postCode = "32725";
		String country = "United States";
		String state = "Florida";
		String telephone = "407-555-0113";
		String password = "Password123";
		String product1Name = "Eos V-Neck Hoodie";
		

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().getCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(6));
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		driver.navigate().to("https://magento2-b2b.magebit.com/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='panel header'] ul li span[class='not-logged-in']")));
				
		driver.findElement(By.cssSelector("div[class='panel header'] ul li:nth-child(3)")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//*[text()='Create New Customer'])[1]")).click();
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("is_subscribed")).click();
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("password-confirmation")).sendKeys(password);
		driver.findElement(By.cssSelector("button[title='Create an Account']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div[data-ui-id='message-success']")).getText(),
				"Thank you for registering with Main Website Store.");
		System.out.println(driver.findElement(By.cssSelector("div[data-ui-id='message-success']")).getText());

		Actions a = new Actions(driver);
		a.moveToElement(driver.findElement(By.xpath("//nav/ul/li[2]"))).build().perform();
		Thread.sleep(2000);
		a.sendKeys(Keys.ARROW_DOWN).build().perform();

		a.sendKeys(Keys.ARROW_RIGHT).build().perform();

		driver.findElement(By.id("ui-id-13")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("page-title-heading")));
		List<WebElement> products = driver.findElements(By.className(".column"));
		WebElement prod = products.stream()
				.filter(product -> product.findElement(By.cssSelector(".product-item-link")).getText().equals(product1Name))
				.findFirst().orElse(null);
		driver.findElement(By.cssSelector("div[id='product-item-info_1202'] div[id ='option-label-size-157-item-171']"))
				.click();
		driver.findElement(By.cssSelector("div[id='product-item-info_1202'] div[id ='option-label-color-93-item-50'"))
				.click();
		driver.findElement(By.cssSelector("div[id='product-item-info_1202'] button[type='submit']")).click();
		Thread.sleep(4000);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div[role='alert'] div[data-ui-id='message-success']")));
		driver.findElement(By.cssSelector(".showcart")).click();
		driver.findElement(By.cssSelector("a[class='action viewcart']")).click();
		List<WebElement> cartProducts = driver
				.findElements(By.cssSelector("tbody[class='cart item'] tr td div strong"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(product1Name));
		Assert.assertTrue(match);
		JavascriptExecutor js = (JavascriptExecutor) driver;
    	js.executeScript("window.scrollBy(0,400)");
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("div[class='cart-summary _sticky'] ul li button :nth-child(1)")).click();
		
		Thread.sleep(6000);
		a.moveToElement(driver.findElement(By.cssSelector("input[name='street[0]']"))).build().perform();
		a.sendKeys(driver.findElement(By.cssSelector("input[name='street[0]']")), address).build().perform();

		WebElement staticDropdown = driver.findElement(By.cssSelector("select[name='country_id']"));
		Select dropdown = new Select(staticDropdown);
		dropdown.selectByVisibleText(country);

		WebElement staticDropdown2 = driver.findElement(By.cssSelector("select[name='region_id']"));
		Select dropdown2 = new Select(staticDropdown2);
		dropdown2.selectByVisibleText(state);

		a.sendKeys(driver.findElement(By.cssSelector("input[name='city']")), city).build().perform();
		a.sendKeys(driver.findElement(By.cssSelector("input[name='postcode']")), postCode).build().perform();
		a.sendKeys(driver.findElement(By.cssSelector("input[name='telephone']")), telephone).build().perform();
		driver.findElement(By.cssSelector("input[value='flatrate_flatrate']")).click();
		driver.findElement(By.cssSelector(".continue")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[text()='Place Order']")).click();
		WebElement successMessage = driver.findElement(By.xpath("//*[text()='Thank you for your purchase!']"));
		Assert.assertTrue(successMessage.getText().equalsIgnoreCase("Thank you for your purchase!"));
	//	driver.close();

	}

}

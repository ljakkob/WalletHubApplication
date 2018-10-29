import org.junit.Assert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;



public class WalletPage {

	private WebDriver driver;
	private Properties properties = new Properties();

	@Before
	public void SetUp() throws Exception {

		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "/Users/larissajacob/chromeDriver/chromedriver");
		driver = new ChromeDriver(options);
		driver.get("https://wallethub.com/join/light");
		properties.load(new FileReader(new File("tests.properties")));
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void CreateAccount() {
		String email = "larissa.jacob01@gmail.com";
		String password = "wallethubApplic@t1on";
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.cssSelector("input[placeholder='Email Address']")).sendKeys(email);
		driver.findElement(By.cssSelector("input[placeholder='Password']")).sendKeys(password);
		driver.findElement(By.cssSelector("button[type=button]")).click();

		WebElement user = driver.findElement(By.cssSelector("a.user"));

		String login = "larissa_jacob01";

		Assert.assertEquals(login, user.getText());

		driver.get("https://wallethub.com/profile/test_insurance_company/");

		WebElement rateHover = driver.findElement(By.className("wh-rating-notes"));
		WebElement ratingStars = driver
				.findElement(By.xpath("//div[@class='wh-rating-choices-holder']/a[contains(text(),'5')]"));

		Actions action = new Actions(driver);
		action.moveToElement(rateHover).perform();
		action.moveToElement(ratingStars).click().perform();

		driver.findElement(By.className("dropdown-list-new")).click();
		driver.findElement(By.cssSelector("a[data-target=Health]")).click();

		WebElement rateHoverReview = driver.findElement(By.id("overallRating"));
		WebElement ratingStarsHealth = driver.findElement(By.cssSelector(".bf-icon-star-empty:nth-child(5)"));

		driver.findElement(By.id("review-content")).sendKeys(properties.getProperty("reviewText"));

		Actions rate = new Actions(driver);
		rate.moveToElement(rateHoverReview).moveToElement(ratingStarsHealth).click().build().perform();

		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		driver.findElement(By.linkText("has been posted."));
		driver.get("https://wallethub.com/profile/larissa_jacob01/reviews/");
		WebElement review = driver.findElement(By.cssSelector(".review-first > p"));

		
		Assert.assertEquals(properties.getProperty("reviewText"), review.getText());

	}
	
		
	@After
	public void quit() {				
		driver.quit();
	}
		
	}


import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class FacebookLogin {
	private WebDriver driver;

	
	@Before
	public void SetUp() {
		 
		 ChromeOptions options = new ChromeOptions(); 
		 options.addArguments("--disable-notifications");
		 System.setProperty("webdriver.chrome.driver", "/Users/larissajacob/chromeDriver/chromedriver");
		 driver =new ChromeDriver(options);
		 driver.get("https://www.facebook.com/");
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		    
	}
	
	
	@Test
	public void FacebookLogin() {
		
		String	email = "josenegrao.brisa@gmail.com";
		String	password= "Vendedor18#";
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.cssSelector("input[value='Log In']")).click();
		driver.findElement(By.xpath("//textarea[@name='xhpc_message']")).sendKeys("Hello Word !!");	
		driver.findElement(By.cssSelector(".\\_1pek:nth-child(1) .\\_1se_")).click();
		driver.findElement(By.cssSelector("button[data-testid=react-composer-post-button]")).click();
		
	}
	
	@After
	public void Quit() {
		driver.close();
	}

}

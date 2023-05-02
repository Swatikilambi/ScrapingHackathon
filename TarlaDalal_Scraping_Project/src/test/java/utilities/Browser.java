package utilities;

import java.time.Duration;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser {

	public static  WebDriver driver;
	static String url= "https://www.tarladalal.com";
	public static void openBrowser()
	{
	//public WebDriver driver;
			ChromeOptions ops = new ChromeOptions();
			
			WebDriverManager.chromedriver().setup();
			
			ops.addArguments("--remote-allow-origins=*");
			// this will make sure that alters does not close automatically 
			//and allow us to handle alert in code.
			ops.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.IGNORE);
			driver = new ChromeDriver(ops);
				
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
			driver.get(url);
	}
}

package Recipes_Scraping;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.tracing.Tags;
import org.testng.annotations.Test;

import com.numpyninja.taraladala.food.categories.RecipeCategory;
import com.numpyninja.taraladala.food.categories.FoodCategory;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Browser;
import utilities.XLUtility;

public class PCOS_Recipe_Scraping extends Browser
{
	
		static String ingd,targetmorbidcondition,RecURL,labels;
	static int cre,i,we,ta,al;
	static WebElement rc,recipeid;
	static int size,recidSize;
	//static String url= "https://www.tarladalal.com";
	//public static  WebDriver driver;
	static List<String> recid=new ArrayList<String>();
	static List<String> rcname=new ArrayList<String>();
	static List<String> preptime=new ArrayList<String>();
	static List<String> cooktime=new ArrayList<String>();
	static List<String> PrepMethod=new ArrayList<String>();
	static List<String> NitriValue=new ArrayList<String>();
	static List<String> ingdList;
	Map<String,List<String>> foodCategoryMap=new HashMap<>();
	
	public static void main(String args[]) throws InterruptedException, IOException
	{
		try {
		openBrowser();
		clickReciep();
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
	}
	
	//@Test(priority=1)
	public static void clickReciep() throws InterruptedException, IOException
	{
		try {
		WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
		recipies.click();
		WebElement PCOSrecipes = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht335']"));
		PCOSrecipes.click();
		
		int pageCount=driver.findElements(By.xpath("//div[@id='pagination']/a")).size();
		System.out.println("Page count:"+pageCount);
		size= driver.findElements(By.xpath("//span[@class='rcc_recipename']")).size();
		System.out.println("recipe size"+size);
		
		targetmorbidcondition= driver.findElement(By.xpath("//span[@id='ctl00_cntleftpanel_lblSearchTerm']//span//h1")).getText();
		System.out.println("Target Morbid Condition :"+targetmorbidcondition);
				
		for(i=2;i<=pageCount+1;i++) {
			for(cre=1;cre<=size;cre++)
			{
				rc=driver.findElement(By.xpath("//article[@class='rcc_recipecard']["+cre+"]//a[@itemprop='url']"));
				rcname.add(rc.getText());
				recipeid=driver.findElement(By.xpath("//article[@class='rcc_recipecard']["+cre+"]//div[@class='rcc_rcpno']//span[contains(text(),'Recipe')]"));
				recid.add(recipeid.getText());
				int recidSize=driver.findElements(By.xpath("//article[@class='rcc_recipecard'][\"+cre+\"]//div[@class='rcc_rcpno']//span[contains(text(),'Recipe')]")).size();
				rc.click();
				PCOS_Elimination_Recipe_Details.checkElimination();
				recid.clear();
				rcname.clear();
				preptime.clear();
				cooktime.clear();
				PrepMethod.clear();
				NitriValue.clear();
				driver.navigate().back();
			}
		WebElement pg= driver.findElement(By.xpath("//a[text()='"+i+"']"));
		pg.click();
		}
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
	}	
}





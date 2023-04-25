package Recipes_Scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.numpyninja.taraladala.Recipe.categories.RecipeCategory;
import com.numpyninja.taraladala.food.categories.FoodCategory;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.XLUtility;


public class Diabetes {
	
	static String ingredientDivs;
	static String Ingredient;
	static String labels;
	static String diabeticEliminateSet;
	static String ingredientText;
	static boolean isIngFoundInElList;
	
	
public static void main(String[] args) throws InterruptedException, IOException
{
	
	
	String path=System.getProperty("user.dir");
	System.setProperty("webdriver.chrome.driver", path+"/src/test/resources/Drivers/chromedriver.exe");
	
	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	
	driver.get("http://www.tarladalal.com");
	
	
	driver.manage().window().maximize();
	Thread.sleep(1000);
	
	WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
	
	recipies.click();
	
	
	
	Thread.sleep(1000);
	
	
	Thread.sleep(2000);
	
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	
	////a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht70'] //Diabetic xpath
	////*[@id=\"ctl00_cntleftpanel_cattreecuisine_tvCuisinet28\"] //Indian xpath
	
	WebElement diabeticrecipes = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht70']"));
	
	//Actions act=new Actions(driver);
	//act.moveToElement(diabeticrecipes).click();
	//js.executeScript("arguments[0].scrollIntoView();", diabeticrecipes);
	diabeticrecipes.click();
	
	//labels = driver.findElement(By.xpath("//div[@id='show_breadcrumb']")).getText();
	
	
	
	
	// Read from excel and populate LinkedList
	List<String> diabeticEliminateSet = new LinkedList<String>();
	diabeticEliminateSet.add("Processed grains-cream of rice");
	diabeticEliminateSet.add("rice flour");
	diabeticEliminateSet.add("rice rava"); diabeticEliminateSet.add("Sugar");
	diabeticEliminateSet.add("White rice"); diabeticEliminateSet.add("White bread");
	diabeticEliminateSet.add("Pasta"); diabeticEliminateSet.add("Sweetened beverages- soda");
	diabeticEliminateSet.add("flavoured water"); diabeticEliminateSet.add("Fruit Juice - Apple Juice");
	diabeticEliminateSet.add("orange juice"); diabeticEliminateSet.add("pomegranate juice"); 
	diabeticEliminateSet.add("Trans fats in margarines"); diabeticEliminateSet.add("peanut butter");
	diabeticEliminateSet.add("spreads"); diabeticEliminateSet.add("frozen foods");
	diabeticEliminateSet.add("Flavoured curd/ yogurt"); diabeticEliminateSet.add("Sweetened breakfast cereals- corn flakes");
	diabeticEliminateSet.add("puffed rice");diabeticEliminateSet.add("bran flakes");
	diabeticEliminateSet.add("instant oatmeal"); diabeticEliminateSet.add("rice rava");
	diabeticEliminateSet.add("Honey"); diabeticEliminateSet.add("Maple Syrup");
	diabeticEliminateSet.add("Jaggery"); diabeticEliminateSet.add("Sweets");
	diabeticEliminateSet.add("Candies"); diabeticEliminateSet.add("Chocolates");
	diabeticEliminateSet.add("Refined/ all purpose flour"); diabeticEliminateSet.add("Alcoholic beverages");
	diabeticEliminateSet.add("Processed meat- Bacon"); diabeticEliminateSet.add("sausages");
	diabeticEliminateSet.add("hot dos"); diabeticEliminateSet.add("deli meats");
	diabeticEliminateSet.add("chciken"); diabeticEliminateSet.add("Jams");
	diabeticEliminateSet.add("Jelly"); diabeticEliminateSet.add("Mango Pickel");
	diabeticEliminateSet.add("cucumber Pickle"); diabeticEliminateSet.add("tomatoes Pickel");
	diabeticEliminateSet.add("Canned pineapple"); diabeticEliminateSet.add("canned peaches");
	diabeticEliminateSet.add("Canned mangos"); diabeticEliminateSet.add("Canned pear");
	diabeticEliminateSet.add("Canned mixed fruit"); diabeticEliminateSet.add("Canned mandarine oranges");
	diabeticEliminateSet.add("Chips"); diabeticEliminateSet.add("Mayonnaise");
	diabeticEliminateSet.add("Palmolein oil"); diabeticEliminateSet.add("Dried food- powedered milk");
	diabeticEliminateSet.add("Dried beans"); diabeticEliminateSet.add("Dried peas");
	diabeticEliminateSet.add("Dried corn"); diabeticEliminateSet.add("Doughnuts");
	diabeticEliminateSet.add("cakes"); diabeticEliminateSet.add("Pastries");
	diabeticEliminateSet.add("cookies"); diabeticEliminateSet.add("croissants");
	diabeticEliminateSet.add("Sweetened tea"); diabeticEliminateSet.add("Coffee");
	diabeticEliminateSet.add("Packaged snacks"); diabeticEliminateSet.add("Soft drinks");
	diabeticEliminateSet.add("Banana"); diabeticEliminateSet.add("melon");
	diabeticEliminateSet.add("Dairy milk"); diabeticEliminateSet.add("butter");
	diabeticEliminateSet.add("Cheese");
	
	// Read from excel and populate LinkedList
		List<String> diabeticToAddSet= new LinkedList<String>();
		diabeticToAddSet.add("Broccoli"); 	diabeticToAddSet.add("Pumpkin");
		diabeticToAddSet.add("Seeds- Pumpkin seeds");  diabeticToAddSet.add("Chia sedds");
		diabeticToAddSet.add("Flaxseeds");  diabeticToAddSet.add("Apples");
		diabeticToAddSet.add("Nuts");  diabeticToAddSet.add("Lady finger/Okra");
		diabeticToAddSet.add("Beans");  diabeticToAddSet.add("Berries- raspberries");
		diabeticToAddSet.add(" strawberries"); diabeticToAddSet.add("blueberries");
		diabeticToAddSet.add(" blackberries"); diabeticToAddSet.add("Eggs");
		diabeticToAddSet.add("Yogurt"); diabeticToAddSet.add("Bitter gaurd");
		diabeticToAddSet.add("Rolled oats"); diabeticToAddSet.add("Steel cut oats");
		diabeticToAddSet.add("chicken"); diabeticToAddSet.add("quinoa");
		diabeticToAddSet.add("Mushroom");
		//C:\\Users\\bernipoornima\\eclipse-workspace\\Recipe_Scrapper\\src\\test\\resources\\recipe.xlsx"
	
	String xlpath=".\\src\\test\\resources\\recipe.xlsx";
	
	XLUtility xlutil=new XLUtility(xlpath);
	
	
	
	//Write headers in excel sheet
	xlutil.setCellData("Diabeties_EliminationList", 0, 0, "Recipe ID");
	xlutil.setCellData("Diabeties_EliminationList", 0, 1, "Recipe Name");
	xlutil.setCellData("Diabeties_EliminationList", 0, 2, "RecipeCategory");
	xlutil.setCellData("Diabeties_EliminationList", 0, 3, "FoodCategory");
	xlutil.setCellData("Diabeties_EliminationList", 0, 4, "Preaption Time");
	xlutil.setCellData("Diabeties_EliminationList", 0, 5, "Cooking Time");
	xlutil.setCellData("Diabeties_EliminationList", 0, 6, "Ingredients");
	xlutil.setCellData("Diabeties_EliminationList", 0, 7, "Method");
	xlutil.setCellData("Diabeties_EliminationList", 0, 8, "Nutrient values");
	xlutil.setCellData("Diabeties_EliminationList", 0, 9, "Morbidity Name");
	xlutil.setCellData("Diabeties_EliminationList", 0, 10, "Recipe URL");
	
	//To Add excel sheet
	xlutil.setCellData("Diabeties_ToAddList", 0, 0, "Recipe ID");
	xlutil.setCellData("Diabeties_ToAddList", 0, 1, "Recipe Name");
	xlutil.setCellData("Diabeties_ToAddList", 0, 2, "RecipeCategory");
	xlutil.setCellData("Diabeties_ToAddList", 0, 3, "FoodCategory");
	xlutil.setCellData("Diabeties_ToAddList", 0, 4, "Preaption Time");
	xlutil.setCellData("Diabeties_ToAddList", 0, 5, "Cooking Time");
	xlutil.setCellData("Diabeties_ToAddList", 0, 6, "Ingredients");
	xlutil.setCellData("Diabeties_ToAddList", 0, 7, "Method");
	xlutil.setCellData("Diabeties_ToAddList", 0, 8, "Nutrient values");
	xlutil.setCellData("Diabeties_ToAddList", 0, 9, "Morbidity Name");
	xlutil.setCellData("Diabeties_ToAddList", 0, 10, "Recipe URL");
	
	
	List<String> recID=new ArrayList<String>();
	List<String> recName=new ArrayList<String>();
	List<String> PrepTime=new ArrayList<String>();
	List<String> CookTime=new ArrayList<String>();
	List<String> Ingredient=new ArrayList<String>();
	
	List<String> Method=new ArrayList<String>();
	List<String> Nutrientvalues=new ArrayList<String>();
	List<String> RecURL=new ArrayList<String>();
	
	Thread.sleep(2000);
	int	sizePagination = driver.findElements(By.xpath("//*[@id=\"pagination\"]/a")).size();
	System.out.println("No of Page" +sizePagination);
	
 
    //List<WebElement> noofrecipes=driver.findElements(By.xpath("//div[2]//article[@class='rcc_recipecard']"));
    //System.out.println(noofrecipes);
	int noofrecipes=driver.findElements(By.xpath("//div[2]//article[@class='rcc_recipecard']")).size();
	System.out.println("No of Recipe" +noofrecipes);
	
	int k = 0;
	int l = 0;
	
	for(int i=2;i<=sizePagination;i++)  //this is for Pagination
	{
		for(int j=1;j<=noofrecipes;j++)   //this is for recipes card
		{   
			Thread.sleep(1000);
			System.out.println("Article card  ..........................." +j);
			WebElement recipeID = driver.findElement(By.xpath("//article["+j+"]/div[2]/span"));
						
			System.out.println(recipeID.getText());
			recID.add(driver.findElement(By.xpath("//article["+j+"]/div[2]/span")).getText());
			
			Thread.sleep(1000);
			WebElement recipeName= driver.findElement(By.xpath("//article["+j+"]/div[3]/span[1]/a"));
			
			System.out.println(recipeName.getText());
			recName.add(driver.findElement(By.xpath("//article["+j+"]/div[3]/span[1]/a")).getText());
			
			Thread.sleep(1000);
			System.out.println(recID.size());
			//System.out.println(recName.size());
			
				
			recipeName.click();
			
			labels = driver.findElement(By.xpath("//div[@id='show_breadcrumb']")).getText();
			
			Thread.sleep(1000);
			WebElement preaptime=driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
			System.out.println(preaptime.getText());
			PrepTime.add(driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText());
			
			Thread.sleep(1000);
			WebElement cooktime=driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
			System.out.println(cooktime.getText());
			CookTime.add(driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText());
			
			Thread.sleep(1000);
			WebElement ingredient=driver.findElement(By.xpath("//*[@id=\"rcpinglist\"]"));
			System.out.println(ingredient.getText());
			Ingredient.add(driver.findElement(By.xpath("//*[@id=\"rcpinglist\"]")).getText());
			
			Thread.sleep(1000);
			
			List<WebElement> ingredientDivs = driver.findElements(By.xpath("//*[@id='rcpinglist']/div/span"));
			Boolean isIngFoundInElList = false;  
			Boolean isIngFoundInAddList = false;
			System.out.println("ingredientDivs Size: " + ingredientDivs.size());
			for(int ing=0;ing<ingredientDivs.size();ing++)
			{
				//if(isIngFoundInElList) {
				//	break;
					//driver.navigate().back();  
				//}
				WebElement ingDiv = ingredientDivs.get(ing);
				String ingredientText = ingDiv.getText();
				System.out.println("Ing Div Text: " + ingDiv.getText());
				////*[@id="rcpinglist"]/div/span[3]/a
				//String ingredientText =  driver.findElement(By.xpath("//*[@id='rcpinglist']/div/span["+ing+"]/a/span")).getText();
				//System.out.println("Ing Text: " + ingredientText);
				for(int x=0;x<diabeticEliminateSet.size();x++) {
					if(ingredientText.toLowerCase().contains(diabeticEliminateSet.get(x).toLowerCase())) {
						isIngFoundInElList = true;
						break;
						//driver.navigate().back();  
						//System.out.println("Skipping current recipe. " );
					}
				
				}
				if(isIngFoundInElList)
				{
					break;
				}
				for(int x=0;x<diabeticToAddSet.size();x++) {
					if(ingredientText.toLowerCase().contains(diabeticToAddSet.get(x).toLowerCase())) {
						isIngFoundInAddList = true;
						break;
						//driver.navigate().back();  
						//System.out.println("Skipping current recipe. " );
					}
				
				}
				if(isIngFoundInAddList)
				{
					break;
				}
				
			}
			if(isIngFoundInElList) 
			{
				System.out.println("******************Skipping current recipe.***************** " +recName);
				//continue;
			}
			else 
			{
				Thread.sleep(1000);
				WebElement method=driver.findElement(By.xpath("//*[@id=\"ctl00_cntrightpanel_pnlRcpMethod\"]"));
				System.out.println(method.getText());
				Method.add(driver.findElement(By.xpath("//*[@id=\"ctl00_cntrightpanel_pnlRcpMethod\"]")).getText());
				
				Thread.sleep(1000);
				WebElement nutrivalue=driver.findElement(By.xpath("//*[@id=\"rcpnuts\"]"));
				System.out.println(nutrivalue.getText());
				Nutrientvalues.add(driver.findElement(By.xpath("//*[@id=\"rcpnuts\"]")).getText());
				
				Thread.sleep(1000);
				String url=driver.getCurrentUrl();			
				System.out.println( driver.getCurrentUrl());
				RecURL.add(url);
				if (isIngFoundInAddList)
				{
					System.out.println("******************Adding current recipe.***************** ");
					l++;
					FoodCategory cat=foodCategory();
					RecipeCategory recCat= recipeCategory();
					xlutil.setCellData("Diabeties_ToAddList",l,0,recID.get(0));
					xlutil.setCellData("Diabeties_ToAddList",l,1, recName.get(0));
					xlutil.setCellData("Diabeties_ToAddList",l,2,recCat.name()) ;
					xlutil.setCellData("Diabeties_ToAddList",l,3,cat.name());
					xlutil.setCellData("Diabeties_ToAddList",l, 4, PrepTime.get(0));
					xlutil.setCellData("Diabeties_ToAddList",l, 5, CookTime.get(0));
					xlutil.setCellData("Diabeties_ToAddList", l, 6, Ingredient.get(0));
					xlutil.setCellData("Diabeties_ToAddList", l, 7, Method.get(0));
					xlutil.setCellData("Diabeties_ToAddList", l, 8, Nutrientvalues.get(0));
					xlutil.setCellData("Diabeties_ToAddList", l, 9, "Diabeties");
					xlutil.setCellData("Diabeties_ToAddList", l, 10, RecURL.get(0));
					
				}
				else 
				{
					
					k++;
					
					FoodCategory cat=foodCategory();
					RecipeCategory recCat= recipeCategory();
					//for(int k=1;k<recID.size();k++)   //this is for getting 
					//{
					xlutil.setCellData("Diabeties_EliminationList",k,0,recID.get(0));
					xlutil.setCellData("Diabeties_EliminationList",k,1, recName.get(0));
					xlutil.setCellData("Diabeties_EliminationList",k,2,recCat.name()) ;
					xlutil.setCellData("Diabeties_EliminationList",k,3,cat.name());
					xlutil.setCellData("Diabeties_EliminationList",k, 4, PrepTime.get(0));
					xlutil.setCellData("Diabeties_EliminationList", k, 5, CookTime.get(0));
					xlutil.setCellData("Diabeties_EliminationList", k, 6, Ingredient.get(0));
					xlutil.setCellData("Diabeties_EliminationList", k, 7, Method.get(0));
					xlutil.setCellData("Diabeties_EliminationList", k, 8, Nutrientvalues.get(0));
					xlutil.setCellData("Diabeties_EliminationList", k, 9, "Diabeties");
					xlutil.setCellData("Diabeties_EliminationList", k, 10, RecURL.get(0));
				}
		
			}
			 recID.clear();
			 recName.clear();
			 PrepTime.clear();
			 CookTime.clear();
			 Ingredient.clear();
			 Method.clear();
			 Nutrientvalues.clear();
			 RecURL.clear();
			 
			 
			 driver.navigate().back();
		}
		System.out.println("******************Pagination number.***************** " + i);
		driver.findElement(By.xpath("//*[@id=\"pagination\"]/a["+i+"]")).click();
		
		
	}
	
	
	
}
	public static FoodCategory foodCategory() {
		//List<String> vegan=new ArrayList<>(Arrays.asList("Butter","Curd/Yogurt","Milk","Honey","Paneer","Egg","Ghee")); 
		
		
		//Set<String> veganList=  vegan.stream().filter(a -> ingredientText.contains(a.toLowerCase())).collect(Collectors.toSet());
		//List<String> veganList = new LinkedList<String>();
		//if(ingredientText.toLowerCase().contains(veganList.get(x).toLowerCase())) 
		
		//if(ingredientDivs.contains("Egg"))
			//return FoodCategory.EGGETAIRIAN;
		 if ((labels.contains("jain")))
			return FoodCategory.JAIN;
		//if(veganList.size()!=0)
			//return FoodCategory.VEGAN;
		else
			return FoodCategory.VEGETAIRIAN;
	}
	
	public static RecipeCategory recipeCategory() {
		
		if ((labels.contains("Breakfast")))
			return RecipeCategory.BREAKFAST;
		else if ((labels.contains("Lunch")))
			return RecipeCategory.LUNCH;
		else if ((labels.contains("Snacks")))
			return RecipeCategory.SNACKS;
		else if ((labels.contains("Dinner")))
			return RecipeCategory.DINNER;
		else if ((labels.contains("Soups")))
			return RecipeCategory.SOUPS;
		else
			return RecipeCategory.NOT_MENTIONED;
	}

}

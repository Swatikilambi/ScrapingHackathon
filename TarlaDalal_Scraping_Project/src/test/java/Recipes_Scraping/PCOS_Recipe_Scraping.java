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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.tracing.Tags;
import org.testng.annotations.Test;

import com.numpyninja.taraladala.Recipe.categories.RecipeCategory;
import com.numpyninja.taraladala.food.categories.FoodCategory;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Browser;
import utilities.XLUtility;

public class PCOS_Recipe_Scraping
{
	
		static String ingd,targetmorbidcondition,RecURL,labels;
	static int cre,i,we,ta;
	static WebElement rc,recipeid;
	static int size,recidSize;
	static String url= "https://www.tarladalal.com";
	public static  WebDriver driver;
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
		clickReciep();
	}
	
	//@Test(priority=1)
	public static void clickReciep() throws InterruptedException, IOException
	{
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
				
		for(i=2;i<=pageCount+1 ;i++) {
			for(cre=1;cre<=size;cre++)
			{
				rc=driver.findElement(By.xpath("//article[@class='rcc_recipecard']["+cre+"]//a[@itemprop='url']"));
				rcname.add(rc.getText());
				recipeid=driver.findElement(By.xpath("//article[@class='rcc_recipecard']["+cre+"]//div[@class='rcc_rcpno']//span[contains(text(),'Recipe')]"));
				recid.add(recipeid.getText());
			int recidSize=driver.findElements(By.xpath("//article[@class='rcc_recipecard'][\"+cre+\"]//div[@class='rcc_rcpno']//span[contains(text(),'Recipe')]")).size();
			System.out.println("Recid size"+recidSize);
			rc.click();
			checkElimination();
			//reciepDetails();
			toAddRecipeDetails();
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
	
	public static void checkElimination() throws IOException
	{
	
		String  projectpath = System.getProperty("user.dir"); 
		String xlpath=projectpath+"/src/test/resources/PCOS_Eliminate_Ingredient_List.xlsx";
		XLUtility reader=new XLUtility(xlpath);
		//XLUtility reader= new XLUtility(projectpath+"/src/test/resources/PCOS_Eliminate_Ingredient_List.xlsx","PCOS_Eliminate_Ingredients");
		int rowCount= reader.getRowCount("PCOS_Eliminate_Ingredients"); 
		System.out.println("Readexcel row count"+rowCount);
		//int colcount= reader.GetColumnCount();
		ingd= driver.findElement(By.xpath("//div[@id='rcpinglist']")).getText().replace("\n", " ").toLowerCase().replace("(", " ").replace(")", " ");
		
		String[] ingdArray=ingd.split(" ");
		ingdList= Arrays.asList(ingdArray);
		int colNum=0;
		List<String> EliminateIngredients=new ArrayList<>();
		for(int rowNum=1; rowNum<rowCount; rowNum++){
			EliminateIngredients.add(reader.getCellData("PCOS_Eliminate_Ingredients",rowNum, colNum));
		}
		
	 Set<String> dupList=  EliminateIngredients.stream().filter(a -> ingdList.contains(a)).collect(Collectors.toSet());
		
		if(dupList.size()==0)
		{
			
			reciepDetails();
		}else {
			System.out.println("***************************************************************************");
			System.out.println("Eliminated ingredient found/This recipe not suitable for PCOS :"+Arrays.toString(dupList.toArray()));
			System.out.println("***************************************************************************");
		}
		}
	
		
	
	public static void reciepDetails() throws IOException
	{
		
		labels = driver.findElement(By.xpath("//div[@id='show_breadcrumb']")).getText();
		String  projectpath = System.getProperty("user.dir"); 
		String writexlpath=projectpath+"/src/test/resources/PCOS_Eliminated_ToAdd_Allergy_Recipe_Details.xlsx";
		XLUtility recipedetailsreader= new XLUtility(writexlpath); //"PCOS_Eliminated_Recipe_Data"
		int Writerowcount= recipedetailsreader.getRowCount("PCOS_Eliminated_Recipe_Data");
		
		//Setting headers in Excel to write in Eliminate ingredient sheet
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 2, 0, "RecipeId");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 3, 0, "Recipe_Name");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 4, 0, "Recoipe Category");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 5, 0, "Food Category");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 6, 0, "Ingredients");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 7, 0, "Preperation Time");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 8, 0, "Cook Time");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 9, 0, "Preperation Method");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 10, 0, "Nutrition Value");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 11, 0, "Targetted Morbid condition");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 12, 0, "Recipe URL");
						
		System.out.println("Writeexcel row count Eliminate ingredieny sheet"+Writerowcount);
		
		WebElement preperationtime=driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
		preptime.add(preperationtime.getText());
		WebElement cookingtime=driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
		cooktime.add(cookingtime.getText());
		WebElement PreperationMethod= driver.findElement(By.xpath("//div[@id='recipe_small_steps']//ol[@itemprop='recipeInstructions']"));
		PrepMethod.add(PreperationMethod.getText());
		
		String RecURL=null;
		try {
			WebElement NutritionValue= driver.findElement(By.xpath("//span[@itemprop='nutrition']"));
			RecURL = driver.getCurrentUrl();
			if(NutritionValue.isDisplayed())
			{
				NitriValue.add(NutritionValue.getText());	
			}
			else
				System.out.println("Nutrition value not mentioned");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		ta++;

		FoodCategory cat=foodCategory();
		RecipeCategory recCat= recipeCategory();
		System.out.println("Start inside for loop");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 2, ta, recid.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 3, ta, rcname.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 4, ta, recCat.name());
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 5, ta, cat.name());
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 6, ta, ingd);
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 7, ta, preptime.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 8, ta, cooktime.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 9, ta, PrepMethod.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 10, ta, NitriValue.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 11, ta, targetmorbidcondition);
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 12, ta, RecURL);
		System.out.println("End inside for loop");
			
		System.out.println("==============================================");
		System.out.println("Recipe ID: "+recid);
		System.out.println("Recipe Name: "+rcname);
		System.out.println("==============================================");
		System.out.println("Ingredients:");
		System.out.println(ingd);
		System.out.println("Preperation Time: "+preptime );
		System.out.println("Cooking Time: "+ cooktime);
		System.out.println("Preperation Method: " );
		System.out.println(PrepMethod);
		System.out.println(NitriValue);
		System.out.println("Recipe URL: "+RecURL);	
		
	}	
	
	public static void toAddRecipeDetails() throws IOException
	{
		List<String> AddIngredients=new ArrayList<>(Arrays.asList("Carrot","Broccoli","Beetroot","Eggplant","Cauliflower","Bitter gourd","Raspberries","Pear","Apple","Banana","Orange","Strawberries"));
		RecURL = driver.getCurrentUrl();
		String  projectpath = System.getProperty("user.dir"); 
		String writexlpath=projectpath+"/src/test/resources/PCOS_Eliminated_ToAdd_Allergy_Recipe_Details.xlsx";
		XLUtility recipedetailsreader= new XLUtility(writexlpath);
		int WriterowcountToAdd= recipedetailsreader.getRowCount("PCOS_ToAdd_Recipe_Data");
		System.out.println("Writeexcel row count for ToAdd Sheet"+WriterowcountToAdd);
	
		//Setting headers in Excel to write in ToAdd ingredient sheet
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 2, 0, "RecipeId");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 3, 0, "Recipe_Name");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 4, 0, "Recoipe Category");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 5, 0, "Food Category");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 6, 0, "Ingredients");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 7, 0, "Preperation Time");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 8, 0, "Cook Time");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 9, 0, "Preperation Method");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 10, 0, "Nutrition Value");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 11, 0, "Targetted Morbid condition");
		recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 12, 0, "Recipe URL");
		
		Set<String> ToAdddupList=  AddIngredients.stream().filter(a -> ingdList.contains(a.toLowerCase())).collect(Collectors.toSet());
		
		
		if(ToAdddupList.size()!=0)
		{
			
			FoodCategory cat=foodCategory();
			RecipeCategory recCat= recipeCategory();
			we++;
			System.out.println("Start inside for loop for Add To ingredients");
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 2, we, recid.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 3, we, rcname.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 4, we, recCat.name());
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 5, we, cat.name());
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 6, we, ingd);
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 7, we, preptime.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 8, we, cooktime.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 9, we, PrepMethod.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 10, we, NitriValue.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 11, we, targetmorbidcondition);
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 12, we, RecURL);
			System.out.println("End inside for loop for Add to Ingredients");
			
		}

	}
	public static FoodCategory foodCategory() {
		List<String> vegan=new ArrayList<>(Arrays.asList("Butter","Curd/Yogurt","Milk","Honey","Paneer","Egg","Ghee")); 
		//List<String> Eggetairian=new ArrayList<>(Arrays.asList("Egg")); 
		//List<String> eggetairian=new ArrayList<>(Arrays.asList("Egg")); 
		//List<String> jain=new ArrayList<>(Arrays.asList("Onion","Garlic")); 
		Set<String> veganList=  vegan.stream().filter(a -> ingdList.contains(a.toLowerCase())).collect(Collectors.toSet());

		
		if(ingdList.contains("Egg"))
			return FoodCategory.EGGETAIRIAN;
		else if ((labels.contains("jain")))
			return FoodCategory.JAIN;
		if(veganList.size()!=0)
			return FoodCategory.VEGAN;
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





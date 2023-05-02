package Recipes_Scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.numpyninja.taraladala.food.categories.RecipeCategory;
import com.numpyninja.taraladala.food.categories.FoodCategory;

import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.XLUtility;
import utilities.Browser;

public class Hypothyroidism extends Browser {
	
	
	static String ingredientDivs;
	static String Ingredient;
	static String labels;
	static String hypotEliminateSet ;
	static String ingredientText;
	static boolean isIngFoundInElList;
	public static void main(String[] args) throws InterruptedException, IOException {
		
		openBrowser();
		
		WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
		recipies.click();
		
		WebElement hypothyroidism = driver.findElement(By.xpath("//*[@id=\"ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht226\"]"));
		hypothyroidism .click();
		
		// Read from excel and populate LinkedList
		List<String> hypotEliminateSet = new LinkedList<String>();
		hypotEliminateSet .add("Tofu"); 		hypotEliminateSet .add("Edamame");
		hypotEliminateSet .add("Tempeh"); 		hypotEliminateSet .add("Cauliflower");
		hypotEliminateSet .add("Cabbage"); 		hypotEliminateSet .add("Broccoli");
		hypotEliminateSet .add("Kale"); 		hypotEliminateSet .add("Spinach");
		hypotEliminateSet .add("Sweet potatoes"); 		hypotEliminateSet .add("Strawberries");
		hypotEliminateSet .add("Pine nuts"); 		hypotEliminateSet .add("Peanuts");
		hypotEliminateSet .add("Peaches"); 		hypotEliminateSet .add("Green tea"); 	
		hypotEliminateSet .add("Coffee"); 		hypotEliminateSet .add("Alcohol"); 	
		hypotEliminateSet .add("Soy milk"); 	hypotEliminateSet .add("White bread");
		hypotEliminateSet .add("Cakes"); 	hypotEliminateSet .add(" pastries");
		hypotEliminateSet .add("Fried food"); 	hypotEliminateSet .add("Sugar");
		hypotEliminateSet .add("Processed food- ham"); 	hypotEliminateSet .add("bacon");
		hypotEliminateSet .add(" salami"); hypotEliminateSet .add("sausages");
		hypotEliminateSet .add("Frozen food"); hypotEliminateSet .add("Gluten");
		hypotEliminateSet .add("Sodas"); hypotEliminateSet .add("Energy drinks containing caffeine");
		hypotEliminateSet .add("Packaged food- noodles"); 	hypotEliminateSet .add("Packaged food- soups");
		hypotEliminateSet .add("Packaged food-salad dressings"); 	hypotEliminateSet .add("Packaged food-sauces");
		 hypotEliminateSet .add("Candies");  
		 
		// Read from excel and populate LinkedList
			List<String>  hypotToAddSet= new LinkedList<String>();
			hypotToAddSet.add("Saltwater fish");  hypotToAddSet.add(" oyesters");
			hypotToAddSet.add(" shellfish"); hypotToAddSet.add("Saltwater fish");
			hypotToAddSet.add("Eggs"); hypotToAddSet.add("Dairy");
			hypotToAddSet.add("Nuts"); hypotToAddSet.add("Chicken");
			hypotToAddSet.add("Pumpkin seeds"); hypotToAddSet.add("Seaweed");
			hypotToAddSet.add("Iodized salt"); hypotToAddSet.add("Brazil nuts");
			hypotToAddSet.add("Blue berries"); hypotToAddSet.add("Low-fat yogurt");
			hypotToAddSet.add("Brown rice"); 		hypotToAddSet.add("quinoa");
			hypotToAddSet.add("Mushroom");
			
			String xlpath=".\\src\\test\\resources\\hyporecipe.xlsx";
			XLUtility xlutil=new XLUtility(xlpath);
			
			//Write headers in excel sheet
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 0, "Recipe ID");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 1, "Recipe Name");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 2, "RecipeCategory");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 3, "FoodCategory");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 4, "Preaption Time");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 5, "Cooking Time");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 6, "Ingredients");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 7, "Method");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 8, "Nutrient values");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 9, "Morbidity Name");
			xlutil.setCellData("Hypothyroidism_EliminationList", 0, 10, "Recipe URL");
			
			//To Add excel sheet
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 0, "Recipe ID");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 1, "Recipe Name");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 2, "RecipeCategory");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 3, "FoodCategory");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 4, "Preaption Time");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 5, "Cooking Time");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 6, "Ingredients");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 7, "Method");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 8, "Nutrient values");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 9, "Morbidity Name");
			xlutil.setCellData("Hypothyroidism_ToAddList", 0, 10, "Recipe URL");
			
			
			List<String> recID=new ArrayList<String>();
			List<String> recName=new ArrayList<String>();
			List<String> PrepTime=new ArrayList<String>();
			List<String> CookTime=new ArrayList<String>();
			List<String> Ingredient=new ArrayList<String>();
			
			List<String> Method=new ArrayList<String>();
			List<String> Nutrientvalues=new ArrayList<String>();
			List<String> RecURL=new ArrayList<String>();
			
			int	sizePagination = driver.findElements(By.xpath("//*[@id=\"pagination\"]/a")).size();
			System.out.println("No of Page" +sizePagination);
			
			int noofrecipes=driver.findElements(By.xpath("//div[2]//article[@class='rcc_recipecard']")).size();
			System.out.println("No of Recipe" +noofrecipes);
			
			int k = 0;
			int l = 0;
			
			for(int i=2;i<=sizePagination;i++)  //this is for Pagination
			{
				for(int j=1;j<=noofrecipes;j++)   //this is for recipes card
				{   
					Thread.sleep(1000);
					System.out.println("Article card  .............." + j);
					WebElement recipeID = driver.findElement(By.xpath("//article["+j+"]/div[2]/span"));
								
					System.out.println(recipeID.getText());
					recID.add(driver.findElement(By.xpath("//article["+j+"]/div[2]/span")).getText());
					
					Thread.sleep(1000);
					WebElement recipeName= driver.findElement(By.xpath("//article["+j+"]/div[3]/span[1]/a"));
					
					System.out.println(recipeName.getText());
					recName.add(driver.findElement(By.xpath("//article["+j+"]/div[3]/span[1]/a")).getText());
					
					Thread.sleep(1000);
					System.out.println(recID.size());
						
					recipeName.click();
					
					labels = driver.findElement(By.xpath("//div[@id='show_breadcrumb']")).getText();
					
					WebElement preaptime=driver.findElement(By.xpath("//time[@itemprop='prepTime']"));
					System.out.println(preaptime.getText());
					PrepTime.add(driver.findElement(By.xpath("//time[@itemprop='prepTime']")).getText());
					
					WebElement cooktime=driver.findElement(By.xpath("//time[@itemprop='cookTime']"));
					System.out.println(cooktime.getText());
					CookTime.add(driver.findElement(By.xpath("//time[@itemprop='cookTime']")).getText());
					
					WebElement ingredient=driver.findElement(By.xpath("//*[@id=\"rcpinglist\"]"));
					System.out.println(ingredient.getText());
					Ingredient.add(driver.findElement(By.xpath("//*[@id=\"rcpinglist\"]")).getText());
			
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
						for(int x=0;x< hypotEliminateSet.size();x++) {
							if(ingredientText.toLowerCase().contains( hypotEliminateSet.get(x).toLowerCase())) {
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
						for(int x=0;x<hypotToAddSet.size();x++) {
							if(ingredientText.toLowerCase().contains(hypotToAddSet.get(x).toLowerCase())) {
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
							xlutil.setCellData("Hypothyroidism_ToAddList",l,0,recID.get(0));
							xlutil.setCellData("Hypothyroidism_ToAddList",l,1, recName.get(0));
							xlutil.setCellData("Hypothyroidism_ToAddList",l,2,recCat.name()) ;
							xlutil.setCellData("Hypothyroidism_ToAddList",l,3,cat.name());
							xlutil.setCellData("Hypothyroidism_ToAddList",l, 4, PrepTime.get(0));
							xlutil.setCellData("Hypothyroidism_ToAddList",l, 5, CookTime.get(0));
							xlutil.setCellData("Hypothyroidism_ToAddList", l, 6, Ingredient.get(0));
							xlutil.setCellData("Hypothyroidism_ToAddList", l, 7, Method.get(0));
							xlutil.setCellData("Hypothyroidism_ToAddList", l, 8, Nutrientvalues.get(0));
							xlutil.setCellData("Hypothyroidism_ToAddList", l, 9, "Hypothyroidism");
							xlutil.setCellData("Hypothyroidism_ToAddList", l, 10, RecURL.get(0));
							
						}
						else 
						{
							k++;
							FoodCategory cat=foodCategory();
							RecipeCategory recCat= recipeCategory();
							xlutil.setCellData("Hypothyroidism_EliminationList",k,0,recID.get(0));
							xlutil.setCellData("Hypothyroidism_EliminationList",k,1, recName.get(0));
							xlutil.setCellData("Hypothyroidism_EliminationList",k,2,recCat.name()) ;
							xlutil.setCellData("Hypothyroidism_EliminationList",k,3,cat.name());
							xlutil.setCellData("Hypothyroidism_EliminationList",k, 4, PrepTime.get(0));
							xlutil.setCellData("Hypothyroidism_EliminationList", k, 5, CookTime.get(0));
							xlutil.setCellData("Hypothyroidism_EliminationList", k, 6, Ingredient.get(0));
							xlutil.setCellData("Hypothyroidism_EliminationList", k, 7, Method.get(0));
							xlutil.setCellData("Hypothyroidism_EliminationList", k, 8, Nutrientvalues.get(0));
							xlutil.setCellData("Hypothyroidism_EliminationList", k, 9, "Hypothyroidism");
							xlutil.setCellData("Hypothyroidism_EliminationList", k, 10, RecURL.get(0));
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
		
		 if ((labels.contains("jain")))
			return FoodCategory.JAIN;
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

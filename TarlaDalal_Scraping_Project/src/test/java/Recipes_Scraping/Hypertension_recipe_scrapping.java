package Recipes_Scraping;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.numpyninja.taraladala.food.categories.FoodCategory;
import com.numpyninja.taraladala.food.categories.RecipeCategory;
import utilities.ExcelSheetReader;
import utilities.Recipe;
import utilities.WritingExcel;
import utilities.XLUtility;
import io.github.bonigarcia.wdm.WebDriverManager;
import utilities.Browser;
public class Hypertension_recipe_scrapping extends Browser {
	static ExcelSheetReader ob = new ExcelSheetReader();
	static WritingExcel ex = new WritingExcel();
	Set<String> hyperTensionRecipes = new HashSet<String>();
	static String labels;
    static HashSet<String> ingredientsSet = new HashSet<String>();
   
	public static void main(String[] args) throws InterruptedException, IOException {
		filterRecipes();
	}
	public static void filterRecipes() throws IOException, InterruptedException {
		String xlpath=".\\src\\test\\resources\\HypertensionRecipes.xlsx";
		XLUtility xlutil=new XLUtility(xlpath);
	    String ingredientText = null;
		
	    openBrowser();
				
		WebElement recipies = driver.findElement(By.xpath("//div[contains(text(),'RECIPES')]"));
		
		recipies.click();
	
		Thread.sleep(1000);
				
		WebElement hyperTensionRecipes = driver.findElement(By.xpath("//a[@id='ctl00_cntleftpanel_ttlhealthtree_tvTtlHealtht167']"));
		hyperTensionRecipes.click();

		int page = driver.findElements(By.xpath("//div[@id='pagination']/a")).size(); 
		System.out.println("no of pages :" +page);


		Set<String> hyperTensionSafeRecipes = new HashSet<String>();
		Set<String> hyperTensionIngredients = ob.fileReader(".\\src\\test\\resources\\HypertensionEliminateIngredients.xlsx");
		Set<String> hyperTensionToAddIngredients = ob.fileReader(".\\src\\test\\resources\\Hypertension_to_add.xlsx");

		for(int i=1;i<=page;i++) {
			
            int noofrecipes=driver.findElements(By.xpath("//article[@class='rcc_recipecard']")).size();
            System.out.println("No of Recipe" +noofrecipes);  
            
            int l =1;
            int k=1;
			for(int j=1;j<=noofrecipes;j++) 
			{
				Thread.sleep(100);
               // HashSet<String> ingredientsSet = new HashSet<String>();

				WebElement recipeName= driver.findElement(By.xpath("//article["+j+"]/div[3]/span[1]/a"));
				
			    String recId = driver.findElement(By.xpath("//article["+j+"]/div[2]/span")).getText();

				recipeName.click();
				labels = driver.findElement(By.xpath("//div[@id='show_breadcrumb']")).getText();

				Thread.sleep(1000);
                List<WebElement> ingredientElements = driver.findElements(By.xpath("//div[@id='rcpinglist']"));
                StringBuilder recipeIngredients = new StringBuilder();
                for (WebElement ingredientElement : ingredientElements) {
                   ingredientText = ingredientElement.getText();
                   System.out.println("ingredientText" +ingredientText);
                    String [] ing = ingredientText.split("\n");
                    for(String in:ing) {
                        ingredientsSet.add(in);
                    }
                    recipeIngredients.append(ingredientElement.getText()).append("\n");
                }
               
                String recipeUrl = driver.getCurrentUrl();
                System.out.println("Recipe Url:\n" + recipeUrl);                
                Recipe r = new Recipe(driver,j);
                boolean b = true;
                boolean c = true;
                for(String s: ingredientsSet) {
                	for(String ig: hyperTensionToAddIngredients) {
                		if(s.toLowerCase().contains(ig.toLowerCase()) && c) {
                			FoodCategory cat=foodCategory();
                			RecipeCategory recCat= recipeCategory();
                			ex.excelOutput(".\\src\\test\\resources\\HypertensionRecipes.xlsx");
                        	xlutil.setCellData("HypertensionAdd2",k,0,recId);
        					xlutil.setCellData("HypertensionAdd2",k,1, r.recName);
        					xlutil.setCellData("HypertensionAdd2",k,2, recCat.name());
        					xlutil.setCellData("HypertensionAdd2",k, 3, cat.name());
        					xlutil.setCellData("HypertensionAdd2",k, 4, r.prepTime);
        					xlutil.setCellData("HypertensionAdd2", k, 5, r.cookTime);
        					xlutil.setCellData("HypertensionAdd2", k, 6, r.ingredient);
        					xlutil.setCellData("HypertensionAdd2", k, 7, r.method);
            				xlutil.setCellData("HypertensionAdd2", k, 8, r.nutrientvalues);
            				xlutil.setCellData("HypertensionAdd2", k, 9, r.morbidCondition);
        					xlutil.setCellData("HypertensionAdd2", k, 10, r.recURL);
        					k++;
        					c= false;
                			break;
                		}
                	}
                	for(String ig: hyperTensionIngredients) {
                		if(s.toLowerCase().contains(ig.toLowerCase())) {
                			b = false;
                			break;
                		}
                	}
                	if(!b)
                		break;
                }
                
                if(b) {
                	FoodCategory cat=foodCategory();
        			RecipeCategory recCat= recipeCategory();
                	ex.excelOutput(".\\src\\test\\resources\\HypertensionRecipes.xlsx");
                	xlutil.setCellData("HypertensionEliminate",l,0,recId);
					xlutil.setCellData("HypertensionEliminate",l,1, r.recName);
					xlutil.setCellData("HypertensionEliminate",l,2, recCat.name());
					xlutil.setCellData("HypertensionEliminate",l, 3, cat.name());
					xlutil.setCellData("HypertensionEliminate",l, 4, r.prepTime);
					xlutil.setCellData("HypertensionEliminate", l,5, r.cookTime);
					xlutil.setCellData("HypertensionEliminate", l, 6, r.ingredient);
					xlutil.setCellData("HypertensionEliminate", l, 7, r.method);
    				xlutil.setCellData("HypertensionEliminate", l, 8, r.nutrientvalues);
    				xlutil.setCellData("HypertensionEliminate", l, 9, r.morbidCondition);
					xlutil.setCellData("HypertensionEliminate", l, 10, r.recURL);
					l++;                	
                	}
                
               
                 
				System.out.println(i+","+j);
                
                //go back for next recipe
				driver.navigate().back();	
				Thread.sleep(1000);

		}
				}
		System.out.println(hyperTensionSafeRecipes);		
	}
	
	public static FoodCategory foodCategory() {
		List<String> vegan = new ArrayList<String>();
		vegan.add("Milk");vegan.add("Curd");vegan.add("Yogurt");vegan.add("Honey");vegan.add("Paneer");vegan.add("Ghee");vegan.add("Egg");
		List<String> veganList= new ArrayList<String>();
		for (String expected: vegan) {
			  if (ingredientsSet.contains(expected)) {
			    System.out.println("Search function verified");
			    veganList.add(expected);
			  } else {
			    }
			  }

		
		if(ingredientsSet.contains("Egg"))
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

package Recipes_Scraping;

import static Recipes_Scraping.PCOS_Recipe_Scraping.NitriValue;
import static Recipes_Scraping.PCOS_Recipe_Scraping.PrepMethod;
import static Recipes_Scraping.PCOS_Recipe_Scraping.cooktime;
import static Recipes_Scraping.PCOS_Recipe_Scraping.ingd;
import static Recipes_Scraping.PCOS_Recipe_Scraping.ingdList;
import static Recipes_Scraping.PCOS_Recipe_Scraping.labels;
import static Recipes_Scraping.PCOS_Recipe_Scraping.preptime;
import static Recipes_Scraping.PCOS_Recipe_Scraping.rcname;
import static Recipes_Scraping.PCOS_Recipe_Scraping.recid;
import static Recipes_Scraping.PCOS_Recipe_Scraping.ta;
import static Recipes_Scraping.PCOS_Recipe_Scraping.targetmorbidcondition;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.numpyninja.taraladala.food.categories.RecipeCategory;
import com.numpyninja.taraladala.food.categories.FoodCategory;

import utilities.XLUtility;

public class PCOS_Elimination_Recipe_Details extends PCOS_Recipe_Scraping{

	public static void checkElimination() throws IOException
	{
		try {
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
			To_Add_Recipe_Details.toAddRecipeDetails();
			PCOS_Allergy_Recipe_Details.checkALlergy();
			
		}else {
			System.out.println("***************************************************************************");
			System.out.println("Eliminated ingredient found/This "+ rcname.get(0)+" recipe not suitable for PCOS :"+Arrays.toString(dupList.toArray()));
			System.out.println("***************************************************************************");
		}
		}catch(WebDriverException e) {
			e.printStackTrace();
		}
	}	
	public static void reciepDetails() throws IOException
	{
		try {
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
			WebElement NutritionValue= driver.findElement(By.xpath("//*[@id='rcpnuts']"));
			RecURL = driver.getCurrentUrl();
			if(NutritionValue.isDisplayed())   //*[@id=\"rcpnuts\"]   //span[@itemprop='nutrition']
			{
				NitriValue.add(NutritionValue.getText());	
			}
			else
				System.out.println("Nutrition value not mentioned");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FoodCategory cat=Food_Recipe_Cateory.foodCategory();
		RecipeCategory recCat= Food_Recipe_Cateory.recipeCategory();
		System.out.println("Start inside for loop");
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 2, ta, recid.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 3, ta, rcname.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 4, ta, recCat.name());
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 5, ta, cat.name());
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 6, ta, ingd);
		if(preptime.size()>0) {
			recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 7, ta, preptime.get(0));
		}else {
			recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 7, ta, "NA");
		}
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 8, ta, cooktime.get(0));
		recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 9, ta, PrepMethod.get(0));
		if(NitriValue.size()>0) {
			recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 10, ta, NitriValue.get(0));
		}else {
			recipedetailsreader.setCellData("PCOS_Eliminated_Recipe_Data", 10, ta, "NA");
			
		}
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
		}catch(WebDriverException e) {
		e.printStackTrace();
		}
	}	
}

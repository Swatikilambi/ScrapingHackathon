package Recipes_Scraping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriverException;

import com.numpyninja.taraladala.food.categories.RecipeCategory;
import com.numpyninja.taraladala.food.categories.FoodCategory;

import utilities.XLUtility;

public class To_Add_Recipe_Details extends PCOS_Recipe_Scraping {
	public static void toAddRecipeDetails() throws IOException
	{
		try {
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
			
			FoodCategory cat=Food_Recipe_Cateory.foodCategory();
			RecipeCategory recCat= Food_Recipe_Cateory.recipeCategory();
			we++;
			System.out.println("Start inside for loop for Add To ingredients");
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 2, we, recid.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 3, we, rcname.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 4, we, recCat.name());
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 5, we, cat.name());
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 6, we, ingd);
			if(preptime.size()>0) {
				recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 7, we, preptime.get(0));
			}else {
				recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 7, we, "NA");
			}
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 8, we, cooktime.get(0));
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 9, we, PrepMethod.get(0));
			if(NitriValue.size()>0) {
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 10, we, NitriValue.get(0));
			}else {
				recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 10, we,"NA");
			}
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 11, we, targetmorbidcondition);
			recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 12, we, RecURL);
			System.out.println("End inside for loop for Add to Ingredients");	
		}
		}catch(WebDriverException e) {
			e.printStackTrace();
		}
	}
}

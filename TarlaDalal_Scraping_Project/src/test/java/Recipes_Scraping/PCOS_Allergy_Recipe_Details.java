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

public class PCOS_Allergy_Recipe_Details extends PCOS_Recipe_Scraping{
	public static void checkALlergy() throws IOException
	{
		try {
		String  projectpath = System.getProperty("user.dir"); 
		String writexlpath=projectpath+"/src/test/resources/PCOS_Eliminated_ToAdd_Allergy_Recipe_Details.xlsx";
		XLUtility recipedetailsreader= new XLUtility(writexlpath);
		int WriterowcountAllergy= recipedetailsreader.getRowCount("PCOS_Allergy_Recipe_Data");
		System.out.println("Writeexcel row count for ToAdd Sheet"+WriterowcountAllergy);
		//Setting headers in Excel to write in Allergy details sheet
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 2, 0, "RecipeId");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 3, 0, "Recipe_Name");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 4, 0, "Recoipe Category");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 5, 0, "Food Category");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 6, 0, "Ingredients");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 7, 0, "Preperation Time");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 8, 0, "Cook Time");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 9, 0, "Preperation Method");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 10, 0, "Nutrition Value");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 11, 0, "Targetted Morbid condition");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 12, 0, "Recipe URL");
				
			RecURL = driver.getCurrentUrl();
			List<String> allergyList=new ArrayList<>(Arrays.asList("Soy","sesame","Milk","peanut","walnut","Egg","Almond","Hazelnut","Pecan","Cashew","Pistachio","Shell fish","Seafood")); 
			Set<String> dupAllergyList=  allergyList.stream().filter(a -> ingdList.contains(a)).collect(Collectors.toSet());
			if(dupAllergyList.size()==0)
			{
			FoodCategory cat=Food_Recipe_Cateory.foodCategory();
			RecipeCategory recCat= Food_Recipe_Cateory.recipeCategory();
			al++;
			System.out.println("Start inside for loop for Add To ingredients");
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 2, al, recid.get(0));
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 3, al, rcname.get(0));
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 4, al, recCat.name());
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 5, al, cat.name());
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 6, al, ingd);
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 7, al, preptime.get(0));
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 8, al, cooktime.get(0));
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 9, al, PrepMethod.get(0));
			if(NitriValue.size()>0) {
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 10, al, NitriValue.get(0));
			}else {
				recipedetailsreader.setCellData("PCOS_ToAdd_Recipe_Data", 10, we,"NA");
			}
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 11, al, targetmorbidcondition);
			recipedetailsreader.setCellData("PCOS_Allergy_Recipe_Data", 12, al, RecURL);
			System.out.println("End inside for loop for Add to Ingredients");
		}
		}
		catch(WebDriverException e)
		{
			e.printStackTrace();
		}
	}
}

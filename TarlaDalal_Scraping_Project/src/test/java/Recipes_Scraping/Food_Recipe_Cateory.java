package Recipes_Scraping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.numpyninja.taraladala.food.categories.RecipeCategory;
import com.numpyninja.taraladala.food.categories.FoodCategory;

public class Food_Recipe_Cateory extends PCOS_Recipe_Scraping{

	public static FoodCategory foodCategory() {
		List<String> vegan=new ArrayList<>(Arrays.asList("Butter","Curd/Yogurt","Milk","Honey","Paneer","Egg","Ghee")); 
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

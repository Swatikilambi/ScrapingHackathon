package utilities;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WritingExcel {
	public void excelOutput(String file) throws IOException
	{
		XLUtility xlutil=new XLUtility(file);
		
		
		
		//Write headers in excel sheet
	    xlutil.setCellData("HypertensionEliminate", 0, 0, "Recipe ID");
		xlutil.setCellData("HypertensionEliminate", 0, 1, "Recipe Name");
		xlutil.setCellData("HypertensionEliminate", 0, 2, "Recipe Category");
		xlutil.setCellData("HypertensionEliminate", 0, 3, "Food Category");
		xlutil.setCellData("HypertensionEliminate", 0, 4, "Preaption Time");
		xlutil.setCellData("HypertensionEliminate", 0, 5, "Cooking Time");
		xlutil.setCellData("HypertensionEliminate", 0, 6, "Ingredients");
		xlutil.setCellData("HypertensionEliminate", 0, 7, "Method");
		xlutil.setCellData("HypertensionEliminate", 0, 8, "Nutrient values");
		xlutil.setCellData("HypertensionEliminate", 0, 9, "Morbid condition");
		xlutil.setCellData("HypertensionEliminate", 0, 10, "Recipe URL");
		
		xlutil.setCellData("HypertensionAdd2", 0, 0, "Recipe ID");
		xlutil.setCellData("HypertensionAdd2", 0, 1, "Recipe Name");
		xlutil.setCellData("HypertensionAdd2", 0, 2, "Recipe Category");
		xlutil.setCellData("HypertensionAdd2", 0, 3, "Food Category");
		xlutil.setCellData("HypertensionAdd2", 0, 4, "Preaption Time");
		xlutil.setCellData("HypertensionAdd2", 0, 5, "Cooking Time");
		xlutil.setCellData("HypertensionAdd2", 0, 6, "Ingredients");
		xlutil.setCellData("HypertensionAdd2", 0, 7, "Method");
		xlutil.setCellData("HypertensionAdd2", 0, 8, "Nutrient values");
		xlutil.setCellData("HypertensionAdd2", 0, 9, "Morbid condition");
		xlutil.setCellData("HypertensionAdd2", 0, 10, "Recipe URL");	
	}

}
